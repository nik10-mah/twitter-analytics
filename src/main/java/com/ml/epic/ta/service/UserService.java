/**
 * 
 */
package com.ml.epic.ta.service;

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;

// TODO: Auto-generated Javadoc
/**
 * For user Managetment.
 *
 * @since Nov 23, 2018
 */
public interface UserService {

	/**
	 * Authenticate users from Aws Cognito.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the string
	 */
	AdminInitiateAuthResult authenticate(String username, String password);
	
	/**
	 * Confirm signup.
	 *
	 * @param emailAddress the email address
	 * @param tempPassword the temp password
	 * @param finalPassword the final password
	 * @param session the session
	 * @return true, if successful
	 */
	boolean confirmSignup(String emailAddress, String tempPassword, String finalPassword,  String session) ;

}
