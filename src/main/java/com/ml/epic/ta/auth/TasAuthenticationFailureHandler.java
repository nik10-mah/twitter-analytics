package com.ml.epic.ta.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * The Class CustomAuthenticationFailureHandler. : For Handling when user is Not authorized.
 */
@Component
public class TasAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	// On Failure
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// check exception
		/*if (exception.getClass().isAssignableFrom(TasAuthenticationException.class)) {
			TasAuthenticationException customAuthenticationException = (TasAuthenticationException) exception;
			Map<String, ?> map = customAuthenticationException.getParams();
			// if CognitoChallengeException
			if (map.get("exceptionName").equals("CognitoChallengeException")) {
				// redirect to challenge page to change password

				request.setAttribute("map", map);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmSignup/challenge");
				dispatcher.forward(request, response);
			}

		}*/
		// if Bad Credentials
		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			// redirect to login page , with error = true , to display error message
			response.sendRedirect("/login?error=true");
		}

	}

}
