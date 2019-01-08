package com.ml.epic.ta.dto;

/**
 * The Class ExecuteQueryDTO.
 */
public class ExecuteQueryDTO {

	String sqlValue;

	String action;

	String sqlName;

	/**
	 * Gets the sql value.
	 *
	 * @return the sql value
	 */
	public String getSqlValue() {
		return sqlValue;
	}

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Gets the sql name.
	 *
	 * @return the sql name
	 */
	public String getSqlName() {
		return sqlName;
	}

	/**
	 * Sets the sql value.
	 *
	 * @param sqlValue the new sql value
	 */
	public void setSqlValue(String sqlValue) {
		this.sqlValue = sqlValue;
	}

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Sets the sql name.
	 *
	 * @param sqlName the new sql name
	 */
	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

}
