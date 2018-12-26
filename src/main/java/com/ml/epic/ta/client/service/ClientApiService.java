package com.ml.epic.ta.client.service;


import java.net.URISyntaxException;

import com.ml.epic.ta.component.impl.EventToPostimpl;

public interface ClientApiService {
	
	public void setRequestJson(EventToPostimpl eventToPost);
	public void start() throws URISyntaxException;
	public void stop(String eventName) throws URISyntaxException; 

}
