package com.ml.epic.ta.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * The Class EventDTO.
 */
public class EventDTO {

	@SerializedName("event_id")
	private String eventId;

	@SerializedName("event_name")
	private String eventName;

	/**
	 * The Keywords to Translate
	 */
	@SerializedName("topics")
	private String keywords;

	/**
	 * Languages in which keywords need to be translated
	 */
	@SerializedName("languages")
	private List<String> targetLangs = new ArrayList<String>();

	/**
	 * The List of translated text. transient Keyword is used to not include
	 * Corresponding Keys conversion to be added in JSON
	 */
	transient private List<CorrespondingKeywordsDTO> listOfCorrespondingKeywordsDto = new ArrayList<CorrespondingKeywordsDTO>();
	// private Map<String, String> translatedText = new HashMap<>() ;

	@SerializedName("keywords_all_languages")
	private List<String> keywordsAllLanguages = new ArrayList<String>();

	/**
	 * Instantiates a new event DTO.
	 */
	public EventDTO() {
		super();
	}

	/**
	 * Instantiates a new event DTO.
	 *
	 * @param eventName                      the event name
	 * @param targetLangs                    the target langs
	 * @param keywords                       the keywords
	 * @param listOfCorrespondingKeywordsDto the list of corresponding keywords dto
	 */
//	public EventDTO(String eventName, List<String> targetLangs, String keywords,
//			List<CorrespondingKeywordsDTO> listOfCorrespondingKeywordsDto) {
//		super();
//		this.eventName = eventName;
//		this.targetLangs = targetLangs;
//		this.keywords = keywords;
//		this.listOfCorrespondingKeywordsDto = listOfCorrespondingKeywordsDto;
//	}

	
	

//	public EventDTO(String eventId, String eventName, String keywords, List<String> targetLangs,
//			List<CorrespondingKeywordsDTO> listOfCorrespondingKeywordsDto, List<String> keywordsAllLanguages) {
//		super();
//		this.eventId = eventId;
//		this.eventName = eventName;
//		this.keywords = keywords;
//		this.targetLangs = targetLangs;
//		this.listOfCorrespondingKeywordsDto = listOfCorrespondingKeywordsDto;
//		this.keywordsAllLanguages = keywordsAllLanguages;
//	}

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
	 * Gets the target langs.
	 *
	 * @return the target langs
	 */
	public List<String> getTargetLangs() {
		return targetLangs;
	}

	/**
	 * Sets the target langs.
	 *
	 * @param targetLangs the new target langs
	 */
	public void setTargetLangs(List<String> targetLangs) {
		this.targetLangs = targetLangs;
	}

	/**
	 * Gets the keywords.
	 *
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * Sets the keywords.
	 *
	 * @param keywords the new keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * Gets the translated text.
	 *
	 * @return the translated text
	 */
	/*
	 * public Map<String, String> getTranslatedText() { return translatedText; }
	 * 
	 *//**
		 * Sets the translated text.
		 *
		 * @param translatedText the translated text
		 *//*
			 * public void setTranslatedText(Map<String, String> translatedText) {
			 * this.translatedText = translatedText; }
			 */

	/**
	 * Gets the List Of CorrespondingKeywordsDto.
	 *
	 * @return the listOfCorrespondingKeywordsDto
	 */
	public List<CorrespondingKeywordsDTO> getListOfCorrespondingKeywordsDto() {
		return listOfCorrespondingKeywordsDto;
	}

	/**
	 * Sets the list of corresponding keywords dto.
	 *
	 * @param listOfCorrespondingKeywordsDto the new list of corresponding keywords
	 *                                       dto
	 */
	public void setListOfCorrespondingKeywordsDto(List<CorrespondingKeywordsDTO> listOfCorrespondingKeywordsDto) {
		this.listOfCorrespondingKeywordsDto = listOfCorrespondingKeywordsDto;
	}

	/**
	 * Gets the event id.
	 *
	 * @return the event id
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * Sets the event id.
	 *
	 * @param eventId the new event id
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * Gets the keywords all languages.
	 *
	 * @return the keywords all languages
	 */
	public List<String> getKeywordsAllLanguages() {
		return keywordsAllLanguages;
	}

	/**
	 * Sets the keywords all languages.
	 *
	 * @param keywordsAllLanguages the new keywords all languages
	 */
	public void setKeywordsAllLanguages(List<String> keywordsAllLanguages) {
		this.keywordsAllLanguages = keywordsAllLanguages;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventDTO [eventId=" + eventId + ", eventName=" + eventName + ", keywords=" + keywords + ", targetLangs="
				+ targetLangs + ", keywordsAllLanguages=" + keywordsAllLanguages + "]";
	}

	

	

	

}
