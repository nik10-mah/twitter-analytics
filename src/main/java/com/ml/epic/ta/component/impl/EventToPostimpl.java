package com.ml.epic.ta.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ml.epic.ta.component.EventToPost;
import com.ml.epic.ta.dto.EventDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class EventDTO
 */
@Component("eventToPost")
public class EventToPostimpl implements EventToPost {
	
	String event_name;
	
	String[] topics;
	
	List<String> languages = new ArrayList<String>();
	
	

	/**
	 * Instantiates a new event DTO.
	 */
	
	//Binding all data of EventDTO with EventToPost to generate JSON later on
	public void setEventToPostimpl(EventDTO eventDTO) {
		this.event_name = eventDTO.getEventName();
		System.out.println(event_name);
		String arrayOfTopics[] = eventDTO.getKeywords().split(",");
		this.topics = arrayOfTopics;
		this.languages = eventDTO.getTargetLangs();
	}

	//Returing the object for conversion to JSON in Clien API Service
	public EventToPostimpl getObject(){
		return this;
	}
	


}
