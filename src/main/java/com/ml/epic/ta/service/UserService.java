/**
 * 
 */
package com.ml.epic.ta.service;

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
	String authenticate(String username, String password);
}
