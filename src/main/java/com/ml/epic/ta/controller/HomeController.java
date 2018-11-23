/**
 * 
 */
package com.ml.epic.ta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class IndexController: the home page Controller
 *
 * 
 * @since Oct 27, 2018
 */
@Controller
public class HomeController {

	
	/**
	 * Home: Default app url will invoke this method
	 *
	 * @return the view to be redirected to
	 */
	@GetMapping(value = "/")
	public ModelAndView login() throws InterruptedException{
		ModelAndView mav =new ModelAndView("login");
		return mav;
	}
	
	@PostMapping(value = "/home")
	public ModelAndView home() throws InterruptedException{
		ModelAndView mav =new ModelAndView("home");
		return mav;
	}
	
	
}
