package com.ml.epic.ta.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.ChallengeNameType;
import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.ml.epic.ta.dto.ConfirmSignUpDTO;
import com.ml.epic.ta.dto.SignUpDTO;
import com.ml.epic.ta.service.UserService;

/**
 * The Class AuthController.
 */
@RestController
public class AuthController {

	@Autowired
	UserService userService;

	/**
	 * Sign up execute.: This will call when we click on Create New User.
	 *
	 * @param signUpDTO the sign up DTO
	 * @return the model and view
	 * @throws InterruptedException the interrupted exception
	 */
	@PostMapping(value = "/signup/execute")
	public ModelAndView signUpExecute(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO) throws InterruptedException {
		ModelAndView mav = new ModelAndView("auth/login");

		HashMap<String, String> map = userService.signUp(signUpDTO);
		String error = (String) map.get("error");
		String success = (String) map.get("success");

		if (null != error) {
			mav = new ModelAndView("auth/signup");
			mav.addObject("error", error);
		} else {
			mav.addObject("success", success);
		}

		return mav;
	}

	/**
	 * Signup: use to open the sign up page at url /signup
	 *
	 * @return the model and view
	 * @throws InterruptedException the interrupted exception
	 */
	@GetMapping(value = "/signup")
	public ModelAndView signup() throws InterruptedException {
		ModelAndView mav = new ModelAndView("auth/signup");
		/*
		 * List<String> invitationTypes = new ArrayList<String>();
		 * invitationTypes.add("SMS(DEFAULT)"); invitationTypes.add("EMAIL");
		 * mav.addObject("invitationTypes", invitationTypes);
		 */
		SignUpDTO signUpDTO = new SignUpDTO();
		mav.addObject("signUpDTO", signUpDTO);
		return mav;
	}

	/**
	 * Confirm signup.
	 *
	 * @param confirmSignUpDto the confirm sign up dto
	 * @return the model and view
	 * @throws InterruptedException the interrupted exception
	 */
	@PostMapping(value = "/confirmSignup/execute")
	public ModelAndView confirmSignup(@ModelAttribute("confirmSignUpDto") ConfirmSignUpDTO confirmSignUpDto)
			throws InterruptedException {

		AdminInitiateAuthResult result = null;
		String email = confirmSignUpDto.getEmail();
		String tempPassword = confirmSignUpDto.getTempPassword();
		String finalPassword = confirmSignUpDto.getNewPassword();
		ModelAndView mav = null;

		try {
			// check for Authentication Email And temporary Password correct or not.
			result = userService.authenticate(email, tempPassword);
		} catch (NotAuthorizedException | UserNotFoundException e) {
			throw new BadCredentialsException("Incorrect username or password");
		}

		// Challege will return for NEW Password Required
		if (!ChallengeNameType.NEW_PASSWORD_REQUIRED.name().equals(result.getChallengeName())) {
			throw new RuntimeException("unexpected challenge: " + result.getChallengeName());
		}
		// Get Session by Email And Temporary Password
		String session = result.getSession();
		// Confirm Signup , Update New Password
		boolean signupConfirmed = userService.confirmSignup(email, tempPassword, finalPassword, session);
		if (signupConfirmed)
			mav = new ModelAndView("home");

		return mav;
	}

	/**
	 * Challenge. URL will redirect to challenge page to change password
	 *
	 * @return the model and view
	 */
	@PostMapping(value = "/confirmSignup/challenge")
	public ModelAndView challenge(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("auth/challenge");
		// if condition to check for hashMap instance to save from unchecked or
		// classCast exception
		if (null != request.getAttribute("map") && request.getAttribute("map") instanceof HashMap) {
			// get Parameters
			HashMap<?, ?> map = (HashMap<?, ?>) request.getAttribute("map");
			// Initilize ConfirmSignUpDTO Object
			ConfirmSignUpDTO confirmSignUpDto = new ConfirmSignUpDTO();
			System.out.println((String) map.get("email"));
			// Set Email
			confirmSignUpDto.setEmail((String) map.get("email"));
			mav.addObject("confirmSignUpDto", confirmSignUpDto);
		}

		return mav;
	}
}
