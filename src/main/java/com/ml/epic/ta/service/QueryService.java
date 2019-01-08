package com.ml.epic.ta.service;

import java.util.List;

import com.ml.epic.ta.model.Query;

/**
 * The Interface QueryService.
 */
public interface QueryService {

	/**
	 * Save.
	 *
	 * @param query the query
	 * @param name  the name
	 * @return the string
	 */
	String save(String query, String name);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Query> findAll();
}
