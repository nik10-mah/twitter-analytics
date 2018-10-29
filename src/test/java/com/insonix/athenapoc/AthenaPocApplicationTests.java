package com.insonix.athenapoc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.services.athena.AmazonAthena;
import com.insonix.athenapoc.utils.AthenaUtils;
import com.insonix.athenapoc.utils.IConstants.AwsAthena;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AthenaPocApplicationTests {

	
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
