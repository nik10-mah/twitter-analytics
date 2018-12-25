package com.ml.epic.ta;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.ml.epic.ta.service.EventService;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamoDBLocalConnectivity.
 */
public class DynamoDBLocalConnectivity extends BaseTest {

	@Autowired
	AmazonDynamoDB amazonDynamoDB;

	@Autowired
	EventService eventService;

	/**
	 * List tables.
	 *
	 * @throws Exception the exception
	 */
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
	@Test
	public void save() {
		eventService.save();
	}

}
