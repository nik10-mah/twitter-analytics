/**
 * 
 */
package com.ml.epic.ta.service;

import java.util.Map;

import com.ml.epic.ta.dto.ExecuteQueryDTO;

/**
 * The Interface AthenaService.
 *
 * 
 * @since Oct 27, 2018
 */
public interface AthenaService {

	/**
	 * Executes the query to athena api And Save the Query in Dynamo DB if it is Execute And Save.
	 *
	 * @param analyzeDto the analyze dto: contains Query Name if any, Query Sql value, Action Save or Save & Execute.
	 * @return the Map<String, Object>: Contain rows and columns
	 * @throws InterruptedException             the interrupted exception
	 */
	Map<String, Object> executeQuery(ExecuteQueryDTO analyzeDto) throws InterruptedException;
	
}
