package com.ml.epic.ta.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ml.epic.ta.dto.CreateEventDTO;
import com.ml.epic.ta.model.Event;
import com.ml.epic.ta.repository.EventRepository;
import com.ml.epic.ta.service.EventService;

/**
 * The Class EventServiceImpl: Service implementation to communicate with
 * EventRepository.
 */
@Service("eventService")
public class EventServiceImpl implements EventService {

	/** The event repository. */
	@Autowired
	EventRepository eventRepository;

	/**
	 * Save : to Save Data. Take createEventDTO as input , Returns string message
	 *        success if saved successfully, else returns null.
	 *
	 * @param createEventDTO the create event DTO
	 * @return the string :
	 */
	@Override
	public String save(CreateEventDTO createEventDTO) {

		String eventResult = null;
		// get user already login
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		// make object to pass database
		Event event = new Event();
		// set values in object
		event.setEventName(createEventDTO.getEventName());
		event.setEventKeywords(createEventDTO.getEventKeywords());
		event.setOwnerOfEvent(username);
		event.setCreatedAt(new Date());
		event.setStatus(createEventDTO.getStatus());
		// save object
		eventRepository.save(event);
		eventResult = "success";
		return eventResult;

	}

}
