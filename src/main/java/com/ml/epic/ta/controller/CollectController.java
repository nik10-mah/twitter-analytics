package com.ml.epic.ta.controller;

import java.net.URISyntaxException;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ml.epic.ta.client.service.ClientApiService;
import com.ml.epic.ta.component.EventToPost;

import com.ml.epic.ta.dto.CreateEventDTO;
import com.ml.epic.ta.dto.EventDTO;
import com.ml.epic.ta.model.Event;
import com.ml.epic.ta.service.AwsTranslateService;
import com.ml.epic.ta.service.EventService;

// TODO: Auto-generated Javadoc
/**
 * The Class CollectController.
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
	
	/** The aws translate service. */
	@Autowired
	AwsTranslateService awsTranslateService;
	
	@Autowired
	EventService eventService;
	
//	 The API Service to call NodeJS API
	@Autowired
	ClientApiService clientApiService;
	
//	The Component to hold the Data of EventDTO
	@Autowired
	EventToPost eventToPost;

	
	/**
	 * Collect.
	 * opens collect section
	 * @return the model and view
	 */
	@GetMapping(value = "/")
	public ModelAndView collect() {
		ModelAndView mav =new ModelAndView("collect/collect");
		CreateEventDTO createEventDTO = new CreateEventDTO();
		mav.addObject("createEventDTO", createEventDTO);
		List<Event> listEvents = eventService.findAll();
		mav.addObject("listEvents", listEvents);
		return mav;
	}
	
	/**
	 * Input create event.
	 * open create Event page
	 * @return the model and view
	 */
	@GetMapping(value = "/createEvent/input")
	public ModelAndView inputCreateEvent() {
		ModelAndView mav =new ModelAndView("input");
		return mav;
	}
	
	/**
	 * Execute create event.
	 * on execute the create event page.
	 * @return the model and view
	 */
	/*@PostMapping(value = "/createEvent/execute")
	public String executeCreateEvent(@RequestBody CreateEventDTO createEventDTO) {
		String result = eventService.save(createEventDTO);	
		ModelAndView mav =new ModelAndView("redirect:/collect/");
		
		return result;
	}*/
	
	@PostMapping(value = "/createEvent/execute")
	public ModelAndView executeCreateEvent(@RequestBody CreateEventDTO createEventDTO) {
		eventService.save(createEventDTO);	
		ModelAndView mav =new ModelAndView("collect/listing");
		List<Event> listEvents = eventService.findAll();
		mav.addObject("listEvents", listEvents);
		return mav;
	}
	
	/**
	 * Input start event.
	 * To Open the Start Event Page
	 * @return the model and view
	 */
	@GetMapping(value = "/startEvent/input")
	public ModelAndView inputStartEvent(@RequestParam String eventName) {
		ModelAndView mav =new ModelAndView("/collect/startEvent");
		// To Display AWS supported languages in Start Event Page, Use getSupportedLanguages method from AWS Translate Service
		List<String> allTargetLangsCodes = awsTranslateService.getSupportedLanguages();
		// Ctreate Empty Object of DTO to initalise the Binding on UI
		EventDTO eventDto = new EventDTO();
		
		mav.addObject("eventDto",eventDto);
		mav.addObject("eventName",eventName);

		mav.addObject("allTargetLangsCodes",allTargetLangsCodes);
		
		System.out.println(eventName);
		
		return mav;
	}
	
	
	/**
	 * Execute start event.
	 *  to execute the start event
	 * @param eventDto the event dto
	 * @return the model and view
	 */
	@PostMapping(value = "/startEvent/execute")
	public ModelAndView executeStartEvent(@ModelAttribute("eventDto") EventDTO eventDto) throws URISyntaxException {
		
		//System.out.println(eventDto.getEventName());
//		Binding the recieved Data with to component
		eventToPost.setEventToPostimpl(eventDto);
		
//		Generating the Json Data from Class eventToPost
		clientApiService.setRequestJson(eventToPost.getObject());
		
//		Sendint the generated data to clientApi Service to start the Stream
		clientApiService.start();

		ModelAndView mav =new ModelAndView("redirect:/collect/");
		return mav;
	}
	
	/**
	 * Translate start event.
	 *
	 *Used to translate the Keywords into the target Languages.
	 *
	 * @param sourceLanguage the source language
	 * @param targetLangs the target langs
	 * @param text the text
	 * @return the model and view
	 */
	@GetMapping(value = "/startEvent/transalate")
	public ModelAndView translateStartEvent(@RequestParam String sourceLanguage, @RequestParam List<String> targetLangs, @RequestParam String text ) {
		// Get Translated Text
		Map<String, String> translatedText = awsTranslateService.findTranslation(sourceLanguage, targetLangs, text);
		
		// Set in view modal
		ModelAndView mav =new ModelAndView("/collect/translatedKeywords");
		mav.addObject("translatedText", translatedText);
		mav.addObject("actionName","Start Event");
		return mav;
	}
	
	/**
	 * Stop event.
	 *
	 * @return the model and view
	 */
	@GetMapping(value = "/stopEvent")
	public ModelAndView stopEvent() throws URISyntaxException {
		
		//Replace the given eventName string with received StringName from stopEvent
		String eventName = "Halloween";
		
//		Sending the event Name of which stream has to be stopped
		clientApiService.stop(eventName);
		
		ModelAndView mav =new ModelAndView("collect/collect");
		mav.addObject("stop","Event Stoped");
		return mav;
	}
	
	/**
	 * Delete event.
	 *
	 * @return the model and view
	 */
	@GetMapping(value = "/deleteEvent")
	public ModelAndView deleteEvent() {
		ModelAndView mav =new ModelAndView("collect/collect");
		mav.addObject("delete","Event Deleted");
		
		return mav;
	}
}
