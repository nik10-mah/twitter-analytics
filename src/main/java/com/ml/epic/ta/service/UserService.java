/**
 * 
 */
package com.ml.epic.ta.service;

import java.util.HashMap;

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.ml.epic.ta.dto.SignUpDTO;

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
	 * Confirm signup: This will call to confirm the SignUp Process.
	 *
	 * @param emailAddress  the email address
	 * @param tempPassword  the temp password
	 * @param finalPassword the final password
	 * @param session       the session
	 * @return true, if successful
	 */
	boolean confirmSignup(String emailAddress, String tempPassword, String finalPassword, String session);

	/**
	 * Sign up: Used to Sign Up A new User.
	 *
	 * @param signUpDto the signUpDto: DTO for bind the form to transfer the data
	 * @return the hash map: HashMap key, value pairs for error, success like
	 * @error: Some Error has occurred, @success: New User Created
	 */
	HashMap<String, String> signUp(SignUpDTO signUpDto);

}
