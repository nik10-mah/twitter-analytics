package com.ml.epic.ta.dto;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class EventDTO.
 */
public class EventDTO {
	
	private String eventName;
	
	
	private List<String> targetLangs = new ArrayList<String>();
	
	/** The keywords to translate */
	private String keywords;
	
	/** The List of translated text. */
	private List<CorrespondingKeywordsDTO> listOfCorrespondingKeywordsDto = new ArrayList<CorrespondingKeywordsDTO>();
	//private Map<String, String> translatedText = new HashMap<>() ;
	
	/**
	 * Instantiates a new event DTO.
	 */
	public EventDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	/**
	 * Instantiates a new event DTO.
	 *
	 * @param eventName the event name
	 * @param targetLangs the target langs
	 * @param keywords the keywords
	 * @param listOfCorrespondingKeywordsDto the list of corresponding keywords dto
	 */
	public EventDTO(String eventName, List<String> targetLangs, String keywords,
			List<CorrespondingKeywordsDTO> listOfCorrespondingKeywordsDto) {
		super();
		this.eventName = eventName;
		this.targetLangs = targetLangs;
		this.keywords = keywords;
		this.listOfCorrespondingKeywordsDto = listOfCorrespondingKeywordsDto;
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
	 *//*
	public Map<String, String> getTranslatedText() {
		return translatedText;
	}

	*//**
	 * Sets the translated text.
	 *
	 * @param translatedText the translated text
	 *//*
	public void setTranslatedText(Map<String, String> translatedText) {
		this.translatedText = translatedText;
	}*/

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
	 * @param listOfCorrespondingKeywordsDto the new list of corresponding keywords dto
	 */
	public void setListOfCorrespondingKeywordsDto(List<CorrespondingKeywordsDTO> listOfCorrespondingKeywordsDto) {
		this.listOfCorrespondingKeywordsDto = listOfCorrespondingKeywordsDto;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventDTO [eventName=" + eventName + ", targetLangs=" + targetLangs + ", keywords=" + keywords
				+ ", listOfCorrespondingKeywordsDto=" + listOfCorrespondingKeywordsDto + "]";
	}

	
	
	
}
