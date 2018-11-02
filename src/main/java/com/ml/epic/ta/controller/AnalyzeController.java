/**
 * 
 */
package com.ml.epic.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.athena.AmazonAthena;
import com.ml.epic.ta.service.AthenaService;

/**
 * The Class IndexController: the home page Controller.
 *
 * @since Oct 27, 2018
 */
@Controller
@RequestMapping("/analyze")
public class AnalyzeController {

	/** The Constant BASE.: Base path of analyzo module folder */
	private static final String BASE = "analyze/";

	@Autowired
	AmazonAthena athenaClient;

	@Autowired
	AthenaService athenaService;

	/**
	 * Input query: Open Query form to input query from user
	 *
	 * @return the model and view
	 */
	@GetMapping(value = "")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView(BASE + "main");
		return mav;
	}
	
	@GetMapping(value = "/query/input")
	public ModelAndView inputQuery() {
		ModelAndView mav = new ModelAndView(BASE + "executeQuery");
		return mav;
	}

	/**
	 * Execute query: Executes an query in athena.
	 *
	 * @param asql
	 *            the asql
	 * @return the model and view
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@PostMapping(value = "/query/execute")
	public ModelAndView executeQuery(@RequestParam("asql") String asql) throws InterruptedException {
		ModelAndView mav = new ModelAndView(BASE + "executeQuery");
		mav.addAllObjects(athenaService.executeQuery(asql));
		mav.addObject("asql", asql);
		return mav;
	}

}
