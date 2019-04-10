package com.ml.epic.ta;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.athena.AmazonAthena;
import com.ml.epic.ta.utils.AthenaUtils;
import com.ml.epic.ta.utils.IConstants.AwsAthena;

/**
 * The Class TasApplicationTests.
 */
/**
 * @since Nov 1, 2018
 */
public class AwsAthenaTests extends BaseTest{
	

	
	@Autowired
	AmazonAthena athenaClient;
	
	public static final Logger log = Logger.getLogger(AwsAthenaTests.class.getName());
	//@Test
	public void executeQuery() throws InterruptedException {

		log.log(Level.INFO, "athenaClient" + athenaClient);

		String queryExecutionId = AthenaUtils.submitAthenaQuery(athenaClient, AwsAthena.ATHENA_SAMPLE_QUERY);

		AthenaUtils.waitForQueryToComplete(athenaClient, queryExecutionId);

		AthenaUtils.processResultRows(athenaClient, queryExecutionId);
	}

}
