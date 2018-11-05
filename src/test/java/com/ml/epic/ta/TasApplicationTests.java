package com.ml.epic.ta;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.athena.AmazonAthena;
import com.ml.epic.ta.utils.AthenaUtils;
import com.ml.epic.ta.utils.IConstants.AwsAthena;

/**
 * The Class TasApplicationTests.
 */
public class TasApplicationTests extends BaseTest{

	
	@Autowired
	AmazonAthena athenaClient;
	
	
	@Test
	public void executeQuery() throws InterruptedException {
		System.out.println("athenaClient " + athenaClient); 
		
		
		 String queryExecutionId = AthenaUtils.submitAthenaQuery(athenaClient, AwsAthena.ATHENA_SAMPLE_QUERY);

		 AthenaUtils.waitForQueryToComplete(athenaClient, queryExecutionId);

		 AthenaUtils.processResultRows(athenaClient, queryExecutionId);
	}

}
