package com.ml.epic.ta.client.service.impl;

import java.net.URISyntaxException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.ml.epic.ta.client.service.ClientService;
import com.ml.epic.ta.dto.EventDTO;
import com.ml.epic.ta.model.Event;
import com.ml.epic.ta.service.EventService;

/**
 * ClientServiceImpl contains method to start & Stop the request to Node API for
 * streaming. Holds the data member BASE_URL of Node API requestJson string to
 * store the data of EventDTO in Json format
 *
 */
@Service("clientService")
public class ClientServiceImpl implements ClientService {

	// private static String BASE_URL = "http://stacksapien.vradars.com:3000/api";
	private static String BASE_URL = "http://192.168.1.5:9000/api/";
	private String requestJson;

	@Autowired
	EventService eventService;

	/**
	 * @param eventDto Receives the EventDTO and converts it to JSON format to send
	 *                 the data to API
	 */
	private void setRequestJson(EventDTO eventDto) {

		requestJson = new Gson().toJson(eventDto);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ml.epic.ta.client.service.ClientService#start(com.ml.epic.ta.dto.
	 * EventDTO) It received the EventDTO converts it to JSON and send the required
	 * data to Node API to start the stream
	 */
	@Override
	public void start(EventDTO eventDto) throws URISyntaxException {

		setRequestJson(eventDto);
		System.out.println("The converted Json is ->\n" + requestJson);
		RestTemplate restTemplate = new RestTemplate();

		// Creating the URL of Node API to start the Stream;
		String URL = BASE_URL + "/event/start";
		// Use MediaType.APPLICATION_JSON_UTF8 , as we have to send translated keywords
		// in different languages , different format
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		String resp = restTemplate.postForObject(URL, entity, String.class);
		System.out.println("The recieved response is ->\n" + resp);
		if (null != resp) {
			this.updateEventForStart(eventDto.getEventId());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ml.epic.ta.client.service.ClientService#stop(java.lang.String)
	 * 
	 * stop(String eventId) recieves the event id of the event as parameter it
	 * requests the Node API to stop the stream.
	 */
	@Override
	public void stop(String eventId) throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();

		String URL = BASE_URL + "/event/stop";

		// String event_name = "{" + "\"event_name\"" + ": " + "\"" + eventName + "\"" +
		// "}";
		String event_id = "{" + "\"event_id\"" + ": " + "\"" + eventId + "\"" + "}";

		// System.out.println("The generated JSON to stop stream is ->\n" + event_name);
		System.out.println("The generated JSON to stop stream is ->\n" + event_id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// HttpEntity<String> entity = new HttpEntity<String>(event_name, headers);
		HttpEntity<String> entity = new HttpEntity<String>(event_id, headers);

		String resp = restTemplate.postForObject(URL, entity, String.class);
		System.out.println("The recieved response is ->\n" + resp);
		if (null != resp) {
			this.updateEventForStop(eventId);
		}
	}

	/**
	 * Update event for start. : Logic to update DB when Events Starts.
	 *
	 * @param eventId the event id
	 * @return the event
	 */
	private Event updateEventForStart(String eventId) {
		Event event = new Event();
		event = eventService.findById(eventId);
		event.setStartedAt(new Date());
		// TO DO: Fetch from constant file.
		event.setStatus("RUNNING");
		// TO DO : COmmon Logic for get logged in user
		// check if user is already login
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		event.setStartedByUser(username);
		event = eventService.updateEvent(event);
		return event;
	}

	/**
	 * Update event for stop. : Logic to update the database table when Event Stops.
	 *
	 * @param eventId the event id
	 * @return the event
	 */
	private Event updateEventForStop(String eventId) {
		Event event = new Event();
		event = eventService.findById(eventId);
		event.setStoppedAt(new Date());
		// TO DO: Fetch from constant file.
		event.setStatus("STOPPED");
		// check if user is already login
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		event.setStoppedByUser(username);
		event = eventService.updateEvent(event);
		return event;
	}
}
