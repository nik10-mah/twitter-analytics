package com.ml.epic.ta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PasswordController {

	/**
	 * Forgot password: Open the forgotPassword page only so that user can email to
	 * get reset link
	 *
	 * @return the model and view
	 * @throws InterruptedException the interrupted exception
	 */
	@GetMapping(value = "/forgotPassword/input")
	public ModelAndView forgotPassword() throws InterruptedException {
		ModelAndView mav = new ModelAndView("password/forgotPassword");
		return mav;
	}

	/**
	 * Reset password: OPen the resetPassword page only when user click on link got
	 * in email, so that user can put new password to reset password
	 *
	 * @return the model and view
	 * @throws InterruptedException the interrupted exception
	 */
	@GetMapping(value = "/resetPassword/input")
	public ModelAndView resetPassword() throws InterruptedException {
		ModelAndView mav = new ModelAndView("password/resetPassword");
		return mav;
	}
}
