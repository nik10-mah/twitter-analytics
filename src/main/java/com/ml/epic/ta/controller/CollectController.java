package com.ml.epic.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ml.epic.ta.service.AwsTranslateService;

@RestController
@RequestMapping("/collect")
public class CollectController {
	
	@Autowired
	AwsTranslateService awsTranslateService;
	
	@GetMapping(value = "/")
	public ModelAndView collect() {
		ModelAndView mav =new ModelAndView("collect/collect");
		return mav;
	}
	
	@GetMapping(value = "/createEvent/input")
	public ModelAndView inputCreateEvent() {
		ModelAndView mav =new ModelAndView("input");
		return mav;
	}
	
	@PostMapping(value = "/createEvent/execute")
	public ModelAndView executeCreateEvent() {
		ModelAndView mav =new ModelAndView("execute");
		return mav;
	}
	
	@GetMapping(value = "/startEvent/input")
	public ModelAndView inputStartEvent() {
		ModelAndView mav =new ModelAndView("/collect/startEvent");
		// To Display AWS supported languages in Start Event Page, Use getSupportedLanguages method from AWS Translate Service
		List<String> langCodes = awsTranslateService.getSupportedLanguages();
		mav.addObject("langCodes",langCodes);
		return mav;
	}

	@PostMapping(value = "/startEvent/execute")
	public ModelAndView executeStartEvent() {
		ModelAndView mav =new ModelAndView("startEvent");
		return mav;
	}
	
	@PostMapping(value = "/startEvent/transalate")
	public ModelAndView translateStartEvent() {
		ModelAndView mav =new ModelAndView("translateStartEvent");
		return mav;
	}
	
	@GetMapping(value = "/stopEvent")
	public ModelAndView stopEvent() {
		ModelAndView mav =new ModelAndView("collect/collect");
		mav.addObject("stop","Event Stoped");
		return mav;
	}
	
	@GetMapping(value = "/deleteEvent")
	public ModelAndView deleteEvent() {
		ModelAndView mav =new ModelAndView("collect/collect");
		mav.addObject("delete","Event Deleted");
		
		return mav;
	}
}
