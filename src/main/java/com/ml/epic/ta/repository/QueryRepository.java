package com.ml.epic.ta.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.ml.epic.ta.model.Query;

/**
 * The Interface QueryRepository: For DB interaction with Query Table.
 */
@EnableScan
public interface QueryRepository extends CrudRepository<Query, String> {

	/**
	 * Find by created by: to get Data created by particular user.
	 *
	 * @param createdBy the created by
	 * @return the list
	 */
	List<Query> findByCreatedBy(String createdBy);
}
