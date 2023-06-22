package com.ml.epic.ta.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.ml.epic.ta.model.Event;

/**
 * The Interface EventRepository.
 */
@EnableScan
public interface EventRepository extends CrudRepository<Event, String>{
	
	/**
	 * Find by owner of event.
	 *
	 * @param ownerOfEvent the owner of event
	 * @return the list
	 */
	List<Event> findByOwnerOfEvent(String ownerOfEvent);
}
