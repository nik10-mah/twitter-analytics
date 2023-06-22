package com.ml.epic.ta;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.ml.epic.ta.dto.CreateEventDTO;
import com.ml.epic.ta.model.Event;
import com.ml.epic.ta.repository.EventRepository;
import com.ml.epic.ta.service.EventService;


/**
 * The Class EventsTests.
 */
public class EventsTests extends BaseTest {

	@Autowired
	AmazonDynamoDB amazonDynamoDB;

	@Autowired
	EventService eventService;
	
	@Autowired
	EventRepository eventR;

	/**
	 * List tables.
	 *
	 * @throws Exception
	 *             the exception
	 */
	//@Test
	public void listTables() throws Exception {

		/*
		 * AmazonDynamoDB amazonDynamoDB =
		 * AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
		 * .withCredentials(new AWSStaticCredentialsProvider( new
		 * BasicAWSCredentials("AKIAJJD372WCZXKUBPEA",
		 * "9Fp4ZnqBbWtFzFwZF20s4SVyX+6V889XZIyXlxPo"))).build();
		 */// new AmazonDynamoDBAsyncClient(new
			// BasicAWSCredentials(awsProperties.getAccessKey(),
			// awsProperties.getSecretKey()));
			// return amazonDynamoDB;
		System.out.println(amazonDynamoDB);
		ListTablesResult listTablesResult = amazonDynamoDB.listTables();
		List<String> tableNames = listTablesResult.getTableNames();
		System.out.println("Number of tables: " + tableNames.size());
		for (String tableName : tableNames) {
			System.out.println("Table: " + tableName);
		}

	}

	/**
	 * Save.
	 */
	public void save() {
		CreateEventDTO createEventDTO = new CreateEventDTO();
		 eventService.save(createEventDTO);
	}

	/**
	 * Find all events.
	 */
	public void findAllEvents() {

		List<?> aoEvents = eventService.findAll();
		assertTrue(aoEvents.size() > 0);
	}
	//@Test
	public void findByOwnerOfEvent() {
		List<Event> list = (List<Event>)eventR.findByOwnerOfEvent("");
		System.out.println(list);
		for(Event event: list ) {
			System.out.println(event);
		}
		System.out.println(list.size());
		assert(list.size()>0);
	}
	public void delete() {
		List<?> aoEventsBeforeDelete = eventService.findAll();
		eventService.deleteById("sds");
		List<?> aoEventsAfterDelete = eventService.findAll();
		assertTrue(aoEventsBeforeDelete.size() - aoEventsAfterDelete.size() == 1);
	}

}
