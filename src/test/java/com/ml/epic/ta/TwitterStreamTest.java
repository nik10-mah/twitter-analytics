package com.ml.epic.ta;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TwitterStreamTest extends BaseTest {

	private static String BASE_URL = "http://localhost:9000/api";

	//@Test
	public void test() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		URI uri = new URI(BASE_URL);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		System.out.println("Response ===> " + result.getBody());
		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("message"));
	}

	//@Test
	public void start() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		String url = BASE_URL + "/event/start";
		String requestJson = "{\"event_name\":\"NewYearEve2019\",\"topics\":[\"party\",\"festa\",\"partido\",\"بركان\",\"كارثة\",\"furacão\",\"desastre\"],\"languages\":[\"en\",\"ar\",\"pt\",\"es\"]}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		String resp = restTemplate.postForObject(url, entity, String.class);
		System.out.println(resp);

	}
	
	//@Test
	public void stop() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		String url = BASE_URL + "/event/stop";
		String requestJson = "{\"event_name\":\"NewYearEve2019\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		String resp = restTemplate.postForObject(url, entity, String.class);
		System.out.println(resp);

	}

}
