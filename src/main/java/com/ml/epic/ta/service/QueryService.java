package com.ml.epic.ta.service;

import java.util.List;

import com.ml.epic.ta.model.Query;

// TODO: Auto-generated Javadoc
/**
 * The Interface QueryService.
 */
public interface QueryService {

	/**
	 * Save : Save Query data.
	 *
	 * @param query the query
	 * @param name  the name
	 * @return the string: success OR null
	 */
	String save(String query, String name);

	/**
	 * Find all.
	 *
	 * @return the list of queries.
	 */
	List<Query> findAll();
	
	/**
	 * Find by created by.
	 *
	 * @param createdBy the created by
	 * @return the list
	 */
	List<Query> findByCreatedBy();
}
