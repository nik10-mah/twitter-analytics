package com.ml.epic.ta.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.epic.ta.auth.CustomizeAuthenticationSuccessHandler;
import com.ml.epic.ta.auth.TasAuthToken;
import com.ml.epic.ta.exceptions.TasAuthenticationException;
import com.ml.epic.ta.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class AwsAuthenticationProvider.: Used to Authenticate the user.
 */
@Component
public class AwsAuthenticationProvider implements AuthenticationProvider {

	
	@Autowired
	UserService userService;

	public static final Logger log = Logger.getLogger(CustomizeAuthenticationSuccessHandler.class.getName());

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	// For authenticate
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		Authentication auth = null;
		AdminInitiateAuthResult result = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			result = userService.authenticate(username, password);
			// if in result there come a challenge name
			if (null != result.getChallengeName()) 
			//if (true)
			
			{
				/*
				 * throw new CognitoChallengeException("Open form for challenge " +
				 * result.getChallengeName(), result.getChallengeName(),
				 * result.getChallengeParameters());
				 */
				
				// Make Map to put values
				
				map.put("challengeName", "NEW_PASSWORD_REQUIRED");
				map.put("msg", "Open form for challenge " + result.getChallengeName());
				log.info("Open form for challenge " + result.getChallengeName());
				// map.put("authSession", result.getSession());
				// result.getSession()
				// map.put("name", result.getChallengeName());
				HashMap<?, ?> userAttributes = null;
				userAttributes = new ObjectMapper().readValue(result.getChallengeParameters().get("userAttributes"),
						HashMap.class);
				map.put("email", userAttributes.get("email"));
				map.put("email_verified", userAttributes.get("email_verified"));
				// throw custom exception , pass map in it
				//throw new TasAuthenticationException(map);
			}
			auth = new TasAuthToken(username, password, new ArrayList<>(),map);

		} catch (NotAuthorizedException | UserNotFoundException e) {
			throw new BadCredentialsException("Incorrect username or password");
		} catch (JsonParseException e) {
			throw new TasAuthenticationException("JSON Parse Error");
		} catch (JsonMappingException e) {
			throw new TasAuthenticationException("JSON Mapping Error");
		} catch (IOException e) {
			throw new TasAuthenticationException("Some Error Occured");
		}

		return auth;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.authentication.AuthenticationProvider#supports(
	 * java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
