/**
 * 
 */
package com.ml.epic.ta.service;

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;

/**
 * For user Managetment.
 *
 * @since Nov 23, 2018
 */
public interface UserService {

	/**
	 * Authenticate users from Aws Cognito
	 *
	 * @param username the username
	 * @param password the password
	 * @return the string
	 */
	AdminInitiateAuthResult authenticate(String username, String password);
}
