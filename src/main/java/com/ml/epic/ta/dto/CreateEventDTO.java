package com.ml.epic.ta.dto;

/**
 * The Class CreateEventDTO.: use to bind the data of Create Event POP up to
 * transfer from Client end to Backend through object.
 */
public class CreateEventDTO {

	String eventName;

	String eventKeywords;

	String status;// At time of Creation of New Event value is CREATED

	/**
	 * Instantiates a new creates the event DTO.
	 */
	public CreateEventDTO() {
		super();
		this.status = "CREATED";
	}

	/**
	 * Gets the event name.
	 *
	 * @return the event name
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * Sets the event name.
	 *
	 * @param eventName the new event name
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * Gets the event keywords.
	 *
	 * @return the event keywords
	 */
	public String getEventKeywords() {
		return eventKeywords;
	}

	/**
	 * Sets the event keywords.
	 *
	 * @param eventKeywords the new event keywords
	 */
	public void setEventKeywords(String eventKeywords) {
		this.eventKeywords = eventKeywords;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	

}
