package com.ml.epic.ta.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * The Class Event. Model class for event.
 */
@DynamoDBTable(tableName = "Events")
public class Event {
    String id;
	String eventName;
	String eventKeywords;
	String translatedKeywords;
	Date startedAt;
	Date stoppedAt;
	Date createdAt;
	Date deletedAt;
	String ownerOfEvent; // (Cognito Username of logged in user will come here)
	String status;// (CREATED, RUNNING, STOPPED OR DELETED)
	String startedByUser;
	String stoppedByUser;

	/**
	 * Instantiates a new event.
	 */
	public Event() {
		super();
	}

	/**
	 * Instantiates a new event.
	 *
	 * @param eventName     the event name
	 * @param eventKeywords the event keywords
	 */
	public Event(String eventName, String eventKeywords) {
		super();
		this.eventName = eventName;
		this.eventKeywords = eventKeywords;
		
	}

	/**
	 * Instantiates a new event.
	 *
	 * @param eventName          the event name
	 * @param eventKeywords      the event keywords
	 * @param translatedKeywords the translated keywords
	 * @param startedAt          the started at
	 * @param stoppedAt          the stopped at
	 * @param createdAt          the created at
	 * @param deletedAt          the deleted at
	 * @param ownerOfEvent       the owner of event
	 * @param status             the status
	 */
	public Event(String eventName, String eventKeywords, String translatedKeywords, Date startedAt, Date stoppedAt,
			Date createdAt, Date deletedAt, String ownerOfEvent, String status) {
		super();
		this.eventName = eventName;
		this.eventKeywords = eventKeywords;
		this.translatedKeywords = translatedKeywords;
		this.startedAt = startedAt;
		this.stoppedAt = stoppedAt;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
		this.ownerOfEvent = ownerOfEvent;
		this.status = status;
	}
	
	
	

	/**
	 * Instantiates a new event.
	 *
	 * @param id the id
	 * @param eventName the event name
	 * @param eventKeywords the event keywords
	 * @param translatedKeywords the translated keywords
	 * @param startedAt the started at
	 * @param stoppedAt the stopped at
	 * @param createdAt the created at
	 * @param deletedAt the deleted at
	 * @param ownerOfEvent the owner of event
	 * @param status the status
	 */
	public Event(String id, String eventName, String eventKeywords, String translatedKeywords, Date startedAt,
			Date stoppedAt, Date createdAt, Date deletedAt, String ownerOfEvent, String status) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.eventKeywords = eventKeywords;
		this.translatedKeywords = translatedKeywords;
		this.startedAt = startedAt;
		this.stoppedAt = stoppedAt;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
		this.ownerOfEvent = ownerOfEvent;
		this.status = status;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@DynamoDBHashKey(attributeName="id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the event name.
	 *
	 * @return the event name
	 */
	@DynamoDBAttribute(attributeName = "EventName")
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

	/**
	 * Gets the translated keywords.
	 *
	 * @return the translated keywords
	 */
	@DynamoDBAttribute(attributeName = "translated_keywords")
	public String getTranslatedKeywords() {
		return translatedKeywords;
	}

	/**
	 * Sets the translated keywords.
	 *
	 * @param translatedKeywords the new translated keywords
	 */
	public void setTranslatedKeywords(String translatedKeywords) {
		this.translatedKeywords = translatedKeywords;
	}

	/**
	 * Gets the started at.
	 *
	 * @return the started at
	 */
	@DynamoDBAttribute(attributeName = "started_at")
	public Date getStartedAt() {
		return startedAt;
	}

	/**
	 * Sets the started at.
	 *
	 * @param startedAt the new started at
	 */
	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	/**
	 * Gets the stopped at.
	 *
	 * @return the stopped at
	 */
	@DynamoDBAttribute(attributeName = "stopped_at")
	public Date getStoppedAt() {
		return stoppedAt;
	}

	/**
	 * Sets the stopped at.
	 *
	 * @param stoppedAt the new stopped at
	 */
	public void setStoppedAt(Date stoppedAt) {
		this.stoppedAt = stoppedAt;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	@DynamoDBAttribute(attributeName = "created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the deleted at.
	 *
	 * @return the deleted at
	 */
	@DynamoDBAttribute(attributeName = "deleted_at")
	public Date getDeletedAt() {
		return deletedAt;
	}

	/**
	 * Sets the deleted at.
	 *
	 * @param deletedAt the new deleted at
	 */
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	/**
	 * Gets the owner of event.
	 *
	 * @return the owner of event
	 */
	@DynamoDBAttribute(attributeName = "cognito_owner")
	public String getOwnerOfEvent() {
		return ownerOfEvent;
	}

	/**
	 * Sets the owner of event.
	 *
	 * @param ownerOfEvent the new owner of event
	 */
	public void setOwnerOfEvent(String ownerOfEvent) {
		this.ownerOfEvent = ownerOfEvent;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	@DynamoDBAttribute(attributeName = "status")
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

	/**
	 * Gets the started by user.
	 *
	 * @return the started by user
	 */
	public String getStartedByUser() {
		return startedByUser;
	}

	/**
	 * Sets the started by user.
	 *
	 * @param startedByUser the new started by user
	 */
	public void setStartedByUser(String startedByUser) {
		this.startedByUser = startedByUser;
	}

	/**
	 * Gets the stopped by user.
	 *
	 * @return the stopped by user
	 */
	public String getStoppedByUser() {
		return stoppedByUser;
	}

	/**
	 * Sets the stopped by user.
	 *
	 * @param stoppedByUser the new stopped by user
	 */
	public void setStoppedByUser(String stoppedByUser) {
		this.stoppedByUser = stoppedByUser;
	}

	

	
}
