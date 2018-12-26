package com.ml.epic.ta.dto;

/**
 * The Class CorrespondingKeywordsDTO.
 */
public class CorrespondingKeywordsDTO {
	
	/** The target lang. Code */
	private String targetLang;
	
	/** The target values. csv formated text after translate */
	private String targetValues;
	
	/**
	 * Instantiates a new corresponding keywords DTO.
	 */
	public CorrespondingKeywordsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new corresponding keywords DTO.
	 *
	 * @param targetLang the target lang
	 * @param targetValues the target values
	 */
	public CorrespondingKeywordsDTO(String targetLang, String targetValues) {
		super();
		this.targetLang = targetLang;
		this.targetValues = targetValues;
	}

	/**
	 * Gets the target lang.
	 *
	 * @return the target lang
	 */
	public String getTargetLang() {
		return targetLang;
	}

	/**
	 * Sets the target lang.
	 *
	 * @param targetLang the new target lang
	 */
	public void setTargetLang(String targetLang) {
		this.targetLang = targetLang;
	}

	/**
	 * Gets the target values.
	 *
	 * @return the target values
	 */
	public String getTargetValues() {
		return targetValues;
	}

	/**
	 * Sets the target values.
	 *
	 * @param targetValues the new target values
	 */
	public void setTargetValues(String targetValues) {
		this.targetValues = targetValues;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CorrespondingKeywordsDTO [targetLang=" + targetLang + ", targetValues=" + targetValues + "]";
	}
	
	
	
}
