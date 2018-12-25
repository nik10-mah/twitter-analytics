package com.ml.epic.ta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.epic.ta.model.Event;
import com.ml.epic.ta.repository.EventRepository;
import com.ml.epic.ta.service.EventService;

/**
 * The Class EventServiceImpl: Service implementation to communicate with EventRepository.
 */
@Service("eventService")
public class EventServiceImpl implements EventService{

	/** The event repository. */
	@Autowired
	EventRepository eventRepository;
	
	/* (non-Javadoc)
	 * @see com.ml.epic.ta.service.EventService#save()
	 */
	@Override
	public void save() {
		try {
		eventRepository.save(new Event("Hallowen-Event", "dress-up")
		
				/*"",
				new Date(),
				new Date(),
				new Date(),
				new Date(),
				"Owner ",
				"Created")*/);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
