/**
 * 
 */
package com.ml.epic.ta.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.amazonaws.services.athena.AmazonAthena;
import com.ml.epic.ta.dto.ExecuteQueryDTO;
import com.ml.epic.ta.model.Query;
import com.ml.epic.ta.repository.QueryRepository;
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

	@Autowired
	QueryRepository queryRepository;

	/**
	 * Executes the query to athena api And Save the Query in Dynamo DB if it is Execute And Save.
	 *
	 * @param analyzeDto the analyze dto: contains Query Name if any, Query Sql value, Action Save or Save & Execute.
	 * @return the Map<String, Object>: Contain rows and columns
	 * @throws InterruptedException             the interrupted exception
	 */
	@Override
	public Map<String, Object> executeQuery(ExecuteQueryDTO analyzeDto) throws InterruptedException {
		Map<String, Object> ret = null;
		if (analyzeDto.getAction().equals("Execute")) {
			ret = this.executeQueryLogic(analyzeDto.getSqlValue());
		} else {
			if (null != analyzeDto.getSqlName() && !analyzeDto.getSqlName().equals("")) {
				ret = this.executeAndSaveQuery(analyzeDto.getSqlValue(), analyzeDto.getSqlName());
			} else {
				ret = new HashMap<>();
				ret.put("error", "To Save Query Kindly Enter Query Name.");
			}
		}

		return ret;
	}

	/**
	 * Execute and save query.
	 *
	 * @param query the query
	 * @param name the name
	 * @return the map
	 * @throws InterruptedException the interrupted exception
	 */
	private Map<String, Object> executeAndSaveQuery(String query, String name) throws InterruptedException {
		// Execute Query
		Map<String, Object> ret = this.executeQueryLogic(query);
		// Save Query Object.
		Query queryObj = new Query();
		queryObj.setCreatedAt(new Date());
		// get user already login
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		queryObj.setName(name);
		queryObj.setValue(query);
		queryObj.setCreatedBy(username);
		queryRepository.save(queryObj);

		return ret;
	}

	/**
	 * Execute query logic.
	 *
	 * @param query the query
	 * @return the map
	 * @throws InterruptedException the interrupted exception
	 */
	private Map<String, Object> executeQueryLogic(String query) throws InterruptedException {
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
