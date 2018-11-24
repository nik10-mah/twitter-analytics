/**
 * 
 */
package com.ml.epic.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	
	@Autowired
	UserService userService;
	
	/**
	 * Home: Default app url will invoke this method
	 *
	 * @return the view to be redirected to
	 */
	@GetMapping(value = {"/", "/login", "/signin"})
	public ModelAndView login() throws InterruptedException{
		ModelAndView mav =new ModelAndView("login");
		return mav;
	}
	
	@GetMapping(value = {"/signout", "/logout"})
	public ModelAndView signout() throws InterruptedException{
		ModelAndView mav =new ModelAndView("login");
		mav.addObject("msg", "You have been successfully logout");
		return mav;
	}
	
	@PostMapping(value = "/home")
	public ModelAndView home() throws InterruptedException{
		ModelAndView mav =new ModelAndView("home");
		return mav;
	}
	
	@PostMapping(value = "/authenticate")
	public ModelAndView authenticate(@RequestParam String username, @RequestParam String password) throws InterruptedException{
		ModelAndView mav =new ModelAndView("redirect:/home");
		String token = userService.authenticate(username, password);
		System.out.println("token" + token);
		return mav;
	}
	
	
}
