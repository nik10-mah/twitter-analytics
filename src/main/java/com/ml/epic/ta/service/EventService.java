package com.ml.epic.ta.service;

import com.ml.epic.ta.dto.CreateEventDTO;

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
}
