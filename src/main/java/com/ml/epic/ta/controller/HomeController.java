/**
 * 
 */
package com.ml.epic.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ml.epic.ta.service.UserService;

/**
 * The Class IndexController: the home page Controller
 *
 * 
 * @since Oct 27, 2018
 */
@Controller
public class HomeController {

	// @Autowired
	// AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	/**
	 * Home: Default app url will invoke this method
	 *
	 * @return the view to be redirected to
	 */
	@GetMapping(value = { "/", "/login", "/signin" })
	public ModelAndView login(@RequestParam(required = false) String error,
			@RequestParam(required = false) String logout) throws InterruptedException {
		ModelAndView mav = new ModelAndView("login");
		
		if (null != error) {
			mav.addObject("error", "Invalid Username or Password");
		}

		// TODO check if user is already login send to home
		return mav;
	}

	@GetMapping(value = { "/signout", "/logout" })
	public ModelAndView signout() throws InterruptedException {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("success", "You have been successfully logout");
		return mav;
	}

	// @GetMapping(value = { "/error" })
	// public ModelAndView error() throws InterruptedException {
	// ModelAndView mav = new ModelAndView("login");
	// mav.addObject("error", "Invalid Username or Password");
	// return mav;
	// }

	@GetMapping(value = "/home")
	public ModelAndView home() throws InterruptedException {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}

	// @PostMapping(value = "/authenticate")
	// public ModelAndView authenticate(@RequestParam String username,
	// @RequestParam String password) throws InterruptedException{
	// ModelAndView mav =new ModelAndView("redirect:/home");
	// Authentication auth = authenticationManager.authenticate(token);
	//
	// String token = userService.authenticate(username, password);
	// System.out.println("token" + token);
	// return mav;
	// }

}
