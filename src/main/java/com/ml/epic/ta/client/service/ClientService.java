package com.ml.epic.ta.client.service;

import java.net.URISyntaxException;

import com.ml.epic.ta.dto.EventDTO;

public interface ClientService {

	public void start(EventDTO eventDto) throws URISyntaxException;

	public void stop(String eventName) throws URISyntaxException;

}
