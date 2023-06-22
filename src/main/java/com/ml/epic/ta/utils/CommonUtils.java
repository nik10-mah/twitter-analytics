package com.ml.epic.ta.utils;

import java.util.Map;

/**
 * CommonUtils: Class for common utility methods to used in multiple places
 */
public class CommonUtils {

	/**
	 * Map values to csv.: Method Takes Map<String, String> , Fetch all values
	 * stored in Map And treutns them as a single string in csv format.
	 *
	 * @param text the text
	 * @return the string builder
	 */
	public StringBuilder mapValuesToCsv(Map<String, String> text) {
		StringBuilder csvValues = new StringBuilder();
		for (Map.Entry<String, String> entry : text.entrySet()) {
			csvValues.append(entry.getValue() + ",");
		}
		csvValues.deleteCharAt(csvValues.length() - 1);
		return csvValues;
	}
}
