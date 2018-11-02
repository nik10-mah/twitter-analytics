/**
 * 
 */
package com.ml.epic.ta.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.athena.AmazonAthena;
import com.ml.epic.ta.service.AthenaService;
import com.ml.epic.ta.utils.AthenaUtils;

/**
 * The Class AthenaServiceImpl.
 *
 * 
 * @since Oct 27, 2018
 */
@Service("athenaService")
public class AthenaServiceImpl implements AthenaService {

	@Autowired
	AmazonAthena athenaClient;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ml.epic.ta.service.AthenaService#executeQuery(java.lang.
	 * String)
	 */
	@Override
	public Map<String, Object> executeQuery(String query) throws InterruptedException {
		Map<String, Object> ret = new HashMap<>();
		// initte athena query execution abnd gets the queryExecutionId
		String queryExecutionId = AthenaUtils.submitAthenaQuery(athenaClient, query);
		// waiting forn query to finish
		AthenaUtils.waitForQueryToComplete(athenaClient, queryExecutionId);
		// geting records as List of Map<String,String> where Map<String,String>
		// is one row with each column name as key and its corresponding value
		// in string
		List<Map<String, String>> rows = AthenaUtils.processResultRows(athenaClient, queryExecutionId);
		ret.put("rows", rows);
		ret.put("columns", rows.get(0).keySet());

		return ret;
	}

}
