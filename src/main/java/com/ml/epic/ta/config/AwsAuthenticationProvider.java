package com.ml.epic.ta.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import com.ml.epic.ta.auth.CustomAuthenticationException;
import com.ml.epic.ta.service.UserService;

/**
 * The Class AwsAuthenticationProvider.
 */
@Component
public class AwsAuthenticationProvider implements AuthenticationProvider {

	/** The user service. */
	@Autowired
	UserService userService;

	// For authenticate
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		Authentication auth = null;
		AdminInitiateAuthResult result = null;
		try {
			
			result = userService.authenticate(username, password);
			// if in result there come a challenge name
			if (null != result.getChallengeName()) {
				/*throw new CognitoChallengeException("Open form for challenge " + result.getChallengeName(),
						result.getChallengeName(), result.getChallengeParameters());*/
				// Make Map to put values
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("exceptionName", "CognitoChallengeException");
				map.put("msg", "Open form for challenge " + result.getChallengeName());
				map.put("name", result.getChallengeName());
				map.put("params", result.getChallengeParameters());
				// throw custom exception , pass map in it
				throw new CustomAuthenticationException(map);
			}
			auth = new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());

		} catch (NotAuthorizedException  | UserNotFoundException e) {
			throw new BadCredentialsException("Incorrect username or password");
		}
		
		

		return auth;

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		// return false;
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
