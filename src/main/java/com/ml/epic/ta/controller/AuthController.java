package com.ml.epic.ta.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
		ModelAndView mav = new ModelAndView("login");

		HashMap<String, String> map = userService.signUp(signUpDTO);
		String error = (String) map.get("error");
		String success = (String) map.get("success");

		if (null != error) {
			mav = new ModelAndView("signup");
			mav.addObject("error", error);
		} else {
			mav.addObject("success", success);
		}

		return mav;
	}
}
