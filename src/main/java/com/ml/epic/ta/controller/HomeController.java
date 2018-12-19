/**
 * 
 */
package com.ml.epic.ta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ml.epic.ta.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class IndexController: the home page Controller.
 *
 * @since Oct 27, 2018
 */
@Controller
public class HomeController {

	/** The user service. */
	@Autowired
	UserService userService;

	/**
	 * Home: Default app url, /login, /signin will invoke this method.
	 *
	 * @param error  the error
	 * @param logout the logout
	 * @return the view to be redirected to
	 * @throws InterruptedException the interrupted exception
	 */
	@GetMapping(value = { "/", "/login", "/signin" })
	public ModelAndView login(@RequestParam(required = false, value = "error") String error,
			@RequestParam(required = false) String logout) throws InterruptedException {

		ModelAndView mav = new ModelAndView("login");
		// System.out.println("\n\n\n"+error+"\n\n\n");
		if (null != error) {
			mav = new ModelAndView("login");
			// Message to Display
			mav.addObject("error", "Invalid Username or Password");
		}

		// check if user is already login send to home
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		// if user already login
		if (null != username && "anonymousUser" != username) {
			mav = new ModelAndView("redirect:/home");
		}
		return mav;
	}

	/**
	 * Signout. Will call on url /signout and /logout
	 *
	 * @return the model and view
	 * @throws InterruptedException the interrupted exception
	 */
	@GetMapping(value = { "/signout", "/logout" })
	public ModelAndView signout(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
		ModelAndView mav = new ModelAndView("login");
		// FOR Log out
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// Log out
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		// Message to Display
		mav.addObject("success", "You have been successfully logout");
		return mav;
	}

	// @GetMapping(value = { "/error" })
	// public ModelAndView error() throws InterruptedException {
	// ModelAndView mav = new ModelAndView("login");
	// mav.addObject("error", "Invalid Username or Password");
	// return mav;
	// }

	/**
	 * Home. will call at url /home
	 *
	 * @return the model and view
	 * @throws InterruptedException the interrupted exception
	 */
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
