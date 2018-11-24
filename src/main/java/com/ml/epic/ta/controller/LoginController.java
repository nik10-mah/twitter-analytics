/**
 * 
 */
package com.ml.epic.ta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class LoginController.
 *
 * @since Nov 23, 2018
 */
@Controller
public class LoginController {

	
	@GetMapping(value = "/login")
	public ModelAndView inputStartEvent() {
		ModelAndView mav =new ModelAndView("/login");
		return mav;
	}
}
