/**
 * 
 */
package com.insonix.athenapoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.athena.AmazonAthena;
import com.insonix.athenapoc.service.AthenaService;

/**
 * The Class IndexController: the home page Controller
 *
 * @author Nikhil Mahajan
 * @since Oct 27, 2018
 */
@Controller
public class IndexController {

	@Autowired
	AmazonAthena athenaClient;
	
	@Autowired
	AthenaService athenaService;
	
	/**
	 * Index: Default app url wil invoke this method
	 *
	 * @return the model and view
	 * @throws InterruptedException the interrupted exception
	 */
	@GetMapping(value = "/")
	public ModelAndView index() throws InterruptedException{
		ModelAndView mav =new ModelAndView("index");
		return mav;
	}
	
	/**
	 * Execute query: Executes an query in athena
	 *
	 * @param asql the asql
	 * @return the model and view
	 * @throws InterruptedException the interrupted exception
	 */
	@PostMapping(value = "/query/execute")
	public ModelAndView executeQuery(@RequestParam("asql") String asql) throws InterruptedException{
		ModelAndView mav =new ModelAndView("index");
		mav.addAllObjects(athenaService.executeQuery(asql));
		mav.addObject("asql", asql);
		return mav;
	}
	
	
}
