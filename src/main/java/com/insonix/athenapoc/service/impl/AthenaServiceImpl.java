/**
 * 
 */
package com.insonix.athenapoc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.athena.AmazonAthena;
import com.insonix.athenapoc.service.AthenaService;
import com.insonix.athenapoc.utils.AthenaUtils;
import com.insonix.athenapoc.utils.IConstants.AwsAthena;

/**
 * @author Nikhil Mahajan
 * 
 * @since Oct 27, 2018
 */
@Service("athenaService")
public class AthenaServiceImpl implements AthenaService {

	@Autowired
	AmazonAthena athenaClient;
	
	@Override
	public Map<String, Object> executeQuery(String query) throws InterruptedException {
		Map<String,Object> ret = new HashMap<>();
		String queryExecutionId = AthenaUtils.submitAthenaQuery(athenaClient, query);
		 
		 AthenaUtils.waitForQueryToComplete(athenaClient, queryExecutionId);
		 List<Map<String, String>> rows = AthenaUtils.processResultRows(athenaClient, queryExecutionId);
		 ret.put("rows", rows);
		 ret.put("columns", rows.get(0).keySet());
		
		return ret;
	}

}
