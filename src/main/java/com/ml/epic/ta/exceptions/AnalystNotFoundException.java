/**
 * 
 */
package com.ml.epic.ta.exceptions;

/**
 * The Class AnalystNotFoundException: Custom Exception class is thrown when
 * login user or anaylst is not found
 *
 * @since Nov 27, 2018
 */
public class AnalystNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public AnalystNotFoundException(String message) {
		super(message);
	}

	
	
}
