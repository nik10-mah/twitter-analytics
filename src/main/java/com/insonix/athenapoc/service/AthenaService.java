/**
 * 
 */
package com.insonix.athenapoc.service;

import java.util.Map;

/**
 * The Interface AthenaService.
 *
 * @author Nikhil Mahajan
 * @since Oct 27, 2018
 */
public interface AthenaService {

	/**
	 * Executes the query to athena api
	 *
	 * @param query
	 *            the athens query
	 * @return the Map<String, Object>: Contain rows and columns
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	Map<String, Object> executeQuery(String query) throws InterruptedException;
}
