package com.ml.epic.ta.auth;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	Map<String, Object> map=null;
	public static final Logger log = Logger.getLogger(CustomizeAuthenticationSuccessHandler.class.getName());
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
       
		log.log(Level.INFO,request.toString());
		log.log(Level.INFO, response.toString());
		log.log(Level.INFO, "authentication detail"+authentication);
		// for type cast safety
		 if(authentication instanceof TasAuthToken) {
			 // get challenge Name if any
			 String challengeName = (String)(((TasAuthToken) authentication).getMap().get("challengeName"));
			 if(null!= challengeName && challengeName.equals("NEW_PASSWORD_REQUIRED")){
				 log.info("Redirect to Challenge Page");
				 // set map attributes in request
				 	request.setAttribute("map", ((TasAuthToken)authentication).getMap());
				 	// forward to Challenge page to change password
					RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmSignup/challenge");
					dispatcher.forward(request, response);
			    	return;
			 }
			 
		 }
		
	
		// if no challenge , redirect to home page
    	response.sendRedirect("/home");

	}

}
