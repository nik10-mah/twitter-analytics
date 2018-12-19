/**
 * 
 */
package com.ml.epic.ta.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeRequest;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.ChallengeNameType;
import com.amazonaws.services.cognitoidp.model.DeliveryMediumType;
import com.amazonaws.services.cognitoidp.model.UserType;
import com.ml.epic.ta.dto.SignUpDTO;
import com.ml.epic.ta.service.UserService;

/**
 * The Class UserServiceImpl.
 *
 * @since Nov 23, 2018
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	AWSCognitoIdentityProvider authClient;

	/**
	 * Authenticate users from Aws Cognito.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the string
	 */
	@Override
	public AdminInitiateAuthResult authenticate(String username, String password) {
		// String token = null;
		final Map<String, String> authParams = new HashMap<>();
		// authParams.put("USERNAME", "nik10mah@gmail.com");
		// authParams.put("PASSWORD", "Nikhil_10");
		authParams.put("USERNAME", username);
		authParams.put("PASSWORD", password);

		final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
		authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withClientId("3045rrdm5kjp5dh3qt85m9tkuu")
				.withUserPoolId("us-east-1_tZBScdml6").withAuthParameters(authParams);

		AdminInitiateAuthResult result = authClient.adminInitiateAuth(authRequest);
		System.out.println(" Challenge " + result.getChallengeName());
		System.out.println(" Result " + result.getAuthenticationResult());
		return result;
	}

	// Method will call to Confirm Sign Up
	public AdminRespondToAuthChallengeResult confirmSignupAuth(String emailAddress, String tempPassword,
			String finalPassword, String session) {

		Map<String, String> challengeResponses = new HashMap<String, String>();
		challengeResponses.put("USERNAME", emailAddress);
		challengeResponses.put("PASSWORD", tempPassword);
		challengeResponses.put("NEW_PASSWORD", finalPassword);

		AdminRespondToAuthChallengeRequest finalRequest = new AdminRespondToAuthChallengeRequest()
				.withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED).withChallengeResponses(challengeResponses)
				.withClientId("3045rrdm5kjp5dh3qt85m9tkuu").withUserPoolId("us-east-1_tZBScdml6").withSession(session);

		AdminRespondToAuthChallengeResult challengeResponse = authClient.adminRespondToAuthChallenge(finalRequest);

		return challengeResponse;

	}

	/**
	 * Confirm signup: This will call to confirm the SignUp Process.
	 *
	 * @param emailAddress  the email address
	 * @param tempPassword  the temp password
	 * @param finalPassword the final password
	 * @param session       the session
	 * @return true, if successful
	 */
	@Override
	public boolean confirmSignup(String emailAddress, String tempPassword, String finalPassword, String session) {
		boolean singupConfirmed = false;
		AdminRespondToAuthChallengeResult challengeResponse = confirmSignupAuth(emailAddress, tempPassword,
				finalPassword, session);

		if (null != challengeResponse.getAuthenticationResult()) {
			// updateCredentialCookies(response,
			// challengeResponse.getAuthenticationResult());
			// reportResult(response, Constants.ResponseMessages.LOGGED_IN);
			singupConfirmed = true;
		} else {
			throw new RuntimeException("unexpected challenge: " + challengeResponse.getChallengeName());
		}
		return singupConfirmed;
	}

	/**
	 * Sign up: Used to Sign Up A new User.
	 *
	 * @param signUpDto the signUpDto: DTO for bind the form to transfer the data
	 * @return the hash map: HashMap key, value pairs for error, success like
	 * @error: Some Error has occurred, @success: New User Created
	 */
	@Override
	public HashMap<String, String> signUp(SignUpDTO signUpDto) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (null != signUpDto.getUsername() && !signUpDto.getUsername().equals("")) {
			this.signUpLogic(signUpDto);
			map.put("success", "User Created. Kindly Login.");
			map.put("error", null);

		} else {
			map.put("error", "Username Required");
			map.put("success", null);
		}

		return map;

	}

	/**
	 * Sign up logic.: This method contains Logic to Send request to Cognito to
	 * Create New User. It takes signUpDto as Parameter and extracts Email Id etc.
	 * from that to send the request to create new User.
	 *
	 * @param signUpDto the sign up dto
	 * @return the user type : It return the object of class
	 *         com.amazonaws.services.cognitoidp.model.UserType
	 */
	UserType signUpLogic(SignUpDTO signUpDto) {

		AdminCreateUserRequest signUpRequest = new AdminCreateUserRequest().withUserPoolId("us-east-1_tZBScdml6")
				.withUsername(signUpDto.getUsername())
				.withUserAttributes(new AttributeType().withName("name").withValue(signUpDto.getUsername()),
						new AttributeType().withName("email").withValue(signUpDto.getUsername()),
						/*
						 * new AttributeType() .withName("phone_number")
						 * .withValue(signUpDto.getPhoneNumber()),
						 */
						new AttributeType().withName("email_verified").withValue("true")
				/*
				 * new AttributeType() .withName("phone_number_verified")
				 * .withValue(signUpDto.getMakePhNoVerifired())
				 */).withDesiredDeliveryMediums(DeliveryMediumType.EMAIL).withForceAliasCreation(Boolean.FALSE);

		AdminCreateUserResult createUserResult = authClient.adminCreateUser(signUpRequest);
		UserType cognitoUser = createUserResult.getUser();

		return cognitoUser;
	}

}
