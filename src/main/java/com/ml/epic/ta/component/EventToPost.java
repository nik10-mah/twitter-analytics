package com.ml.epic.ta.component;

import com.ml.epic.ta.component.impl.EventToPostimpl;
import com.ml.epic.ta.dto.EventDTO;

public interface EventToPost {
	public void setEventToPostimpl(EventDTO eventDTO);
	public EventToPostimpl getObject();

}
