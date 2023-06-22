/**
 * 
 */
package com.ml.epic.ta.exceptions;

import java.util.Map;

/**
 * The Class CognitoChallengeException: Is thrown when a Aws Cognito send back a
 * challenge to complete
 *
 * @since Nov 27, 2018
 */
public class CognitoChallengeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CognitoChallengeException(String message, String challengeName, Map<String, String> challengeParams)
	{
		super(message);
	}

}
