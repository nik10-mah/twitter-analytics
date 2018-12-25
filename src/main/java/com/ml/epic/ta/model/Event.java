package com.ml.epic.ta.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * The Class Event.
 */
@DynamoDBTable(tableName = "Events")
public class Event {
	
	/** The event name. */
	String eventName;
	
	/** The event keywords. */
	String eventKeywords;
	
	/**
	 * Instantiates a new event.
	 */
	/*String translatedKeywords;
	Date startedAt;
	Date stoppedAt;
	Date createdAt;
	Date deletedAt;
	String ownerOfEvent; // (Cognito Username of logged in user will come here)
	String status;// (CREATED, RUNNING, STOPPED OR DELETED)
*/
	public Event() {
		super();
	}

	/**
	 * Instantiates a new event.
	 *
	 * @param eventName the event name
	 * @param eventKeywords the event keywords
	 */
	public Event(String eventName, String eventKeywords) {
		super();
		this.eventName = eventName;
		this.eventKeywords = eventKeywords;
		/*this.translatedKeywords = translatedKeywords;
		this.startedAt = startedAt;
		this.stoppedAt = stoppedAt;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
		this.ownerOfEvent = ownerOfEvent;
		this.status = status;*/
	}

	/**
	 * Gets the event name.
	 *
	 * @return the event name
	 */
	@DynamoDBHashKey(attributeName = "EventName")
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
	@DynamoDBAttribute(attributeName = "Keywords")
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

	/*public String getTranslatedKeywords() {
		return translatedKeywords;
	}

	public void setTranslatedKeywords(String translatedKeywords) {
		this.translatedKeywords = translatedKeywords;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public Date getStoppedAt() {
		return stoppedAt;
	}

	public void setStoppedAt(Date stoppedAt) {
		this.stoppedAt = stoppedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public String getOwnerOfEvent() {
		return ownerOfEvent;
	}

	public void setOwnerOfEvent(String ownerOfEvent) {
		this.ownerOfEvent = ownerOfEvent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}*/

}
