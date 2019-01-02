package com.ml.epic.ta.service;

import java.util.List;

import com.ml.epic.ta.dto.CreateEventDTO;
import com.ml.epic.ta.model.Event;

/**
 * The Interface EventService.
 */
public interface EventService {

	/**
	 * Save : to Save Data. Take createEventDTO as input , Returns string message
	 *        success if saved successfully, else returns null.
	 *
	 * @param createEventDTO the create event DTO
	 * @return the string :
	 */
	String save(CreateEventDTO createEventDTO);


	/**
	 * Find all events in database
	 *
	 * @return the list
	 */

	List<Event> findAll();

	/**
	 * Delete by id.: Handles the delete process. Takes event id .
	 *
	 * @param id the id
	 */
	void deleteById(String id);


	/**
	 * Find by id. : Used to fetch data for one records by ID
	 *
	 * @param eventId the event id
	 * @return the event
	 */
	Event findById(String eventId);
	
	/**
	 * Update event. handles the update request
	 *
	 * @param event the event
	 * @return the event
	 */
	public Event updateEvent(Event event);
}
