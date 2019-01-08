/**
 * 
 */
package com.ml.epic.ta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.athena.AmazonAthena;
import com.ml.epic.ta.dto.ExecuteQueryDTO;
import com.ml.epic.ta.dto.EventDTO;
import com.ml.epic.ta.model.Event;
import com.ml.epic.ta.model.Query;
import com.ml.epic.ta.model.SampleQuery;
import com.ml.epic.ta.service.AthenaService;
import com.ml.epic.ta.service.AwsTranslateService;
import com.ml.epic.ta.service.EventService;
import com.ml.epic.ta.service.QueryService;

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
	
	@Autowired
	AwsTranslateService awsTranslateService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	QueryService queryService;

	/**
	 * Input query: Open Query form to input query from user.
	 *
	 * @return the model and view
	 */
	@GetMapping(value = "")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView(BASE + "main");
		return mav;
	}
	
	/**
	 * Input query.
	 *
	 * @return the model and view
	 */
	@GetMapping(value = "/query/input")
	public ModelAndView inputQuery() {
		ModelAndView mav = new ModelAndView(BASE + "executeQuery");
		//mav.addObject("mapObj", SampleQuery.values());
		//mav.addAllObjects(this.setModal());
		List<Event> allEventsList = eventService.findAll();
		mav.addObject("allEventsList", allEventsList);
		ExecuteQueryDTO executeQueryDto = new ExecuteQueryDTO();
		mav.addObject("executeQueryDto", executeQueryDto);
		List<Query> allQueryList = queryService.findAll();
		mav.addObject("allQueryList", allQueryList);

		
		return mav;
	}

	/**
	 * Execute query: Executes/save an query in athena/db.
	 *
	 * @param asql
	 *            the asql
	 * @return the model and view
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@PostMapping(value = "/query/execute")
	public ModelAndView executeQuery(@ModelAttribute("executeQueryDto") ExecuteQueryDTO executeQueryDto) throws InterruptedException {
		ModelAndView mav = new ModelAndView(BASE + "executeQuery");
		System.out.println(executeQueryDto);
		Map<String,Object> map = athenaService.executeQuery(executeQueryDto);
		map.putAll(this.setModal());
		mav.addAllObjects(map);
		// keep all values in UI that user already selected for execute/save query.
		mav.addObject("executeQueryDto", executeQueryDto);
		// list all events
		List<Event> allEventsList = eventService.findAll();
		mav.addObject("allEventsList", allEventsList);
		List<Query> allQueryList = queryService.findAll();
		mav.addObject("allQueryList", allQueryList);
		return mav;
	}
	
	
	
	
	/**
	 * Dashboard.
	 *
	 * @return the model and view
	 */
	@GetMapping(value = "/dashboard")
	public ModelAndView dashboard() {
		ModelAndView mav = new ModelAndView(BASE + "dashboard");
		return mav;
	}
	
	/**
	 * Extract images.
	 *
	 * @return the model and view
	 */
	@GetMapping(value = "/extractImages")
	public ModelAndView extractImages() {
		ModelAndView mav = new ModelAndView(BASE + "extractImages");
		// To Display AWS supported languages in Start Event Page, Use getSupportedLanguages method from AWS Translate Service
				List<String> allTargetLangsCodes = awsTranslateService.getSupportedLanguages();
				// Ctreate Empty Object of DTO to initalise the Binding on UI
				EventDTO eventDto = new EventDTO();
				
				mav.addObject("eventDto",eventDto);
				//mav.addObject("eventName",eventName);

				mav.addObject("allTargetLangsCodes",allTargetLangsCodes);
				mav.addObject("actionName","Extract Images");
				//System.out.println(eventName);
		return mav;
	}
	
	@GetMapping(value = "/transalate")
	public ModelAndView translateStartEvent(@RequestParam String sourceLanguage, @RequestParam List<String> targetLangs, @RequestParam String text ) {
		// Get Translated Text
		Map<String, String> translatedText = awsTranslateService.findTranslation(sourceLanguage, targetLangs, text);
		
		// Set in view modal
		ModelAndView mav =new ModelAndView("/collect/translatedKeywords");
		mav.addObject("translatedText", translatedText);
		mav.addObject("actionName","Export Images");
		return mav;
	}
	
	/**
	 * User activity.
	 *
	 * @return the model and view
	 */
	@GetMapping(value = "/dashboard/userActivity")
	public ModelAndView userActivity() {
		ModelAndView mav = new ModelAndView(BASE + "userActivity");
		return mav;
	}
	
	/**
	 * Streams activity.
	 *
	 * @return the model and view
	 */
	@GetMapping(value = "/dashboard/streamsActivity")
	public ModelAndView streamsActivity() {
		ModelAndView mav = new ModelAndView(BASE + "streamsActivity");
		return mav;
	}
	
	/**
	 * Sets the modal.
	 *
	 * @return the map
	 */
	private Map<String,Object> setModal() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sampleQueries", SampleQuery.values());
		return map;
	}

}
