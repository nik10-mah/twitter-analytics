/**
 * 
 */
package com.ml.epic.ta.service.impl;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeRequest;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.ChallengeNameType;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ml.epic.ta.service.UserService#authenticate(java.lang.String,
	 * java.lang.String)
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
	
	//Method will call to Confirm Sign Up
	public AdminRespondToAuthChallengeResult confirmSignupAuth(String emailAddress, String tempPassword, String finalPassword,  String session) {
		
		Map<String,String> challengeResponses = new HashMap<String,String>();
        challengeResponses.put("USERNAME", emailAddress);
        challengeResponses.put("PASSWORD", tempPassword);
        challengeResponses.put("NEW_PASSWORD", finalPassword);
        
        
	 AdminRespondToAuthChallengeRequest finalRequest = new AdminRespondToAuthChallengeRequest()
             .withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
             .withChallengeResponses(challengeResponses)
             .withClientId("3045rrdm5kjp5dh3qt85m9tkuu")
             .withUserPoolId("us-east-1_tZBScdml6")
             .withSession(session);
	 
     AdminRespondToAuthChallengeResult challengeResponse = authClient.adminRespondToAuthChallenge(finalRequest);
     
     return challengeResponse;

	}
	
	/* (non-Javadoc)
	 * @see com.ml.epic.ta.service.UserService#confirmSignup(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean confirmSignup(String emailAddress, String tempPassword, String finalPassword,  String session) {
		boolean singupConfirmed = false;
		AdminRespondToAuthChallengeResult challengeResponse = confirmSignupAuth(emailAddress, tempPassword, finalPassword, session);
		
		 if (null != challengeResponse.getAuthenticationResult())
         {
             //updateCredentialCookies(response, challengeResponse.getAuthenticationResult());
             //reportResult(response, Constants.ResponseMessages.LOGGED_IN);
			 singupConfirmed = true;
         }
         else
         {
             throw new RuntimeException("unexpected challenge: " + challengeResponse.getChallengeName());
         }
		return singupConfirmed;
	}

}
