package com.ml.epic.ta.client.service.impl;





import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.google.gson.Gson;
import com.ml.epic.ta.client.service.*;
import com.ml.epic.ta.component.impl.EventToPostimpl;


@Service("clientApiService")
public class ClientApiServiceImpl implements ClientApiService {
	
	
	public static String BASE_URL = "http://stacksapien.vradars.com:3000/api";
	public String requestJson;
	
	
	//	Created the Json String out of eventToPost class which will be sent as JSON request later
	//	on to start the stream.
	public void setRequestJson(EventToPostimpl eventToPost){
		//System.out.println(eventToPost.toString());
		
		//Converting the Received Event Data to Stream to JSON Type
		requestJson = new Gson().toJson(eventToPost);
	}
	
	@Override
	public void start() throws URISyntaxException{
		
			System.out.println("The converted Json is ->\n"+requestJson);
			RestTemplate restTemplate = new RestTemplate();
			
			//Creating the URL of Node API to start the Stream;
			String URL = BASE_URL+"/event/start";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
			String resp = restTemplate.postForObject(URL, entity, String.class);
			System.out.println("The recieved response is ->\n"+resp);
		
	}

	@Override
	public void stop(String eventName) throws URISyntaxException{
		
		RestTemplate restTemplate = new RestTemplate();
		
		String URL = BASE_URL+"/event/stop";
		
		String event_name = "{"+"\"event_name\""+": "+"\""+eventName+"\""+"}";
		System.out.println("The generated JSON to stop stream is ->\n"+event_name);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(event_name, headers);
		String resp = restTemplate.postForObject(URL, entity, String.class);
		System.out.println("The recieved response is ->\n"+resp);
		
	}
	
	
	
}
