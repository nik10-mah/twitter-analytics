package com.ml.epic.ta.auth;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.ml.epic.ta.auth.CustomAuthenticationException;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomAuthenticationFailureHandler.
 */
@Component
public class CustomAuthenticationFailureHandler  extends SimpleUrlAuthenticationFailureHandler{


	// On Failure
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// check exception
	  if(exception.getClass().isAssignableFrom(CustomAuthenticationException.class)) {
			CustomAuthenticationException customAuthenticationException = (CustomAuthenticationException)exception;
			Map<String,?> map = customAuthenticationException.getParams();
			// if CognitoChallengeException
			if(map.get("exceptionName").equals("CognitoChallengeException")) {
				// redirect to challenge page to change password
				  response.sendRedirect("/login/challenge");
			}
			
		}
		// if Bad Credentials
	   else if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
		   // redirect to login page , with error = true , to display error message
		   response.sendRedirect("/login?error=true");		  
		}
		
	
  	}

}
