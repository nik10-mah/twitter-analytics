package com.ml.epic.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ml.epic.ta.dto.ForgotPasswordDTO;
import com.ml.epic.ta.service.PasswordService;

/**
 * The Class PasswordController.
 */
@RestController
public class PasswordController {

	@Autowired
	PasswordService passwordService;

	/**
	 * Forgot password input. : to Initiate the Forgot Password Recovery Process.
	 * Sends the OTP in email to user to change password.
	 *
	 * @return the model and view
	 * @throws InterruptedException the interrupted exception
	 */
	@GetMapping(value = "/forgotPassword/input")
	public ModelAndView forgotPasswordInput() throws InterruptedException {

		ModelAndView mav = new ModelAndView("password/forgot");
		mav.addObject("forgotPasswordDto", new ForgotPasswordDTO(null, null, null));
		mav.addObject("otp", null);

		return mav;
	}

	/**
	 * Forget password execute. takes ForgotPasswordDTO that includes otp, username,
	 * newPassword and resets the password.
	 *
	 * @param forgotPasswordDto the forgot password dto
	 * @return the model and view
	 * @throws InterruptedException the interrupted exception
	 */
	@PostMapping(value = "/forgotPassword/execute")
	public ModelAndView forgetPasswordExecute(@ModelAttribute("forgotPasswordDto") ForgotPasswordDTO forgotPasswordDto)
			throws InterruptedException {

		ModelAndView mav = new ModelAndView("password/forgot");
		// if some try to access /forgotPassword/execute , redirect to Login
		if (null == forgotPasswordDto.getOtp() && null == forgotPasswordDto.getUsername()
				&& null == forgotPasswordDto.getNewPassword()) {
			mav = new ModelAndView("auth/login");
		} else if (null != forgotPasswordDto.getOtp() && null != forgotPasswordDto.getUsername()
				&& null != forgotPasswordDto.getNewPassword()) {
			mav = new ModelAndView("auth/login");
			// Reset Password
			passwordService.forgotPasswordExecute(forgotPasswordDto);

		}
		// When only username, NO OTP, NO Password ---- when just email to get OTP
		else if (null == forgotPasswordDto.getOtp() && null != forgotPasswordDto.getUsername()
				&& null == forgotPasswordDto.getNewPassword()) {

			String username = forgotPasswordDto.getUsername();
			passwordService.forgetPasswordInit(username);
			// Reload the page with username And display OTP, New Password Section.
			ForgotPasswordDTO forgotPasswordDto2 = new ForgotPasswordDTO();
			forgotPasswordDto2.setUsername(username);
			// New object for Form Binding.
			mav.addObject("forgotPasswordDto", forgotPasswordDto2);
			// To DIsplay Hidden Area of OTP and New Password.
			mav.addObject("otp", "true");

		} else {
			mav = new ModelAndView("auth/login");

		}
		return mav;
	}
}
