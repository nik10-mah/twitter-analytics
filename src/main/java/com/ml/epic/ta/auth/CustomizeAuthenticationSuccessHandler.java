package com.ml.epic.ta.auth;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	public static final Logger log = Logger.getLogger(CustomizeAuthenticationSuccessHandler.class.getName());
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		log.info(request.toString());
		log.log(Level.INFO, response.toString());
		log.log(Level.INFO, authentication.toString());
    	response.sendRedirect("/home");

	}

}
