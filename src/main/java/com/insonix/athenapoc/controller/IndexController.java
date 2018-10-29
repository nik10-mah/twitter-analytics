/**
 * 
 */
package com.insonix.athenapoc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.athena.AmazonAthena;
import com.insonix.athenapoc.service.AthenaService;
import com.insonix.athenapoc.utils.AthenaUtils;
import com.insonix.athenapoc.utils.IConstants.AwsAthena;

/**
 * @author Nikhil Mahajan
 * 
 * @since Oct 27, 2018
 */
@Controller
public class IndexController {

	@Autowired
	AmazonAthena athenaClient;
	
	@Autowired
	AthenaService athenaService;
	
	@GetMapping(value = "/")
	public ModelAndView index() throws InterruptedException{
		ModelAndView mav =new ModelAndView("index");
//		 String queryExecutionId = AthenaUtils.submitAthenaQuery(athenaClient, AwsAthena.ATHENA_SAMPLE_QUERY);
//		 System.out.println("Query Execution id is" + queryExecutionId);
//		 AthenaUtils.waitForQueryToComplete(athenaClient, queryExecutionId);
//		 List<Map<String, String>> rows = AthenaUtils.processResultRows(athenaClient, queryExecutionId);
//		 mav.addObject("rows", rows);
//		 mav.addObject("columns", rows.get(0).keySet());
	
		return mav;
	}
	
	@PostMapping(value = "/query/execute")
	public ModelAndView executeQuery(@RequestParam("asql") String asql) throws InterruptedException{
		ModelAndView mav =new ModelAndView("index");
		mav.addAllObjects(athenaService.executeQuery(asql));
		mav.addObject("asql", asql);
		return mav;
	}
	
	
}
