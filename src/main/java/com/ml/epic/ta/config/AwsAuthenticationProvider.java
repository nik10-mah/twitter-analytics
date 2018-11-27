package com.ml.epic.ta.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.ml.epic.ta.auth.CognitoChallengeException;
import com.ml.epic.ta.service.UserService;

@Component
public class AwsAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		Authentication auth = null;
		System.out.println("Provider " + username + "   " + password);
		try {

			AdminInitiateAuthResult result = userService.authenticate(username, password);
			if (null != result.getChallengeName()) {
				throw new CognitoChallengeException("Open form for challenge " + result.getChallengeName(),
						result.getChallengeName(), result.getChallengeParameters());
			}
			auth = new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());

		} catch (NotAuthorizedException e) {
			throw new BadCredentialsException("Incorrect username or password");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return auth;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		// return false;
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
