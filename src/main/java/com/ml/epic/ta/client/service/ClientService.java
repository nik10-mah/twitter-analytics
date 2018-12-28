package com.ml.epic.ta.client.service;

import java.net.URISyntaxException;

import com.ml.epic.ta.dto.EventDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ClientService.
 */
public interface ClientService {

	/**
	 * Start.  handle the start request
	 *
	 * @param eventDto the event dto
	 * @throws URISyntaxException the URI syntax exception
	 */
	public void start(EventDTO eventDto) throws URISyntaxException;

	/**
	 * Stop. handles the stop event streaming request.
	 *
	 * @param eventId the event id
	 * @throws URISyntaxException the URI syntax exception
	 */
	public void stop(String eventId) throws URISyntaxException;

}
