/**
 * 
 */
package com.ml.epic.ta.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.athena.model.InvalidRequestException;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.InvalidPasswordException;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.ml.epic.ta.dto.ForgotPasswordDTO;
import com.ml.epic.ta.dto.SignUpDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorController: For handling all the exception globally Any
 * exception occured will redirect user to Home Page.
 *
 * @since 29-Oct-2018
 */
@ControllerAdvice
public class ErrorController {

	/**
	 * Error Handler for Athena API request exception for invlaid syntax.
	 *
	 * @param ex the InvalidRequestException
	 * @return the model and view
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(InvalidRequestException.class)
	public ModelAndView athenaInvalidSyntax(InvalidRequestException ex) {
		return this.handleRedirect(ex);
	}
	
	/**
	 * Dynamo resource not found : Handles the Exception when TableName/ Resources not found in DB.
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView dynamoResourceNotFound(ResourceNotFoundException ex) {
		return this.handleRedirect(ex);
	}

	/**
	 * Handle not found: genric handler for rest of the exceptions.
	 *
	 * @param ex the Exeception
	 * @return the model and view
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(Exception.class)
	public ModelAndView handleNotFound(Exception ex) {
		return this.handleRedirect(ex);
	}

	/**
	 * Sign up. handler for exception if exception comes during signup
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(InvalidParameterException.class)
	public ModelAndView signUp(InvalidParameterException ex) {
		return this.handleRedirectForgotPassword(ex);
	}

	/**
	 * Sign up UserAlreadyExist. During Creating New User handler for exception if
	 * exception comes for UserAlreadyExist
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(UsernameExistsException.class)
	public ModelAndView signUpUserAlreadyExist(UsernameExistsException ex) {
		return this.handleRedirectSignup(ex);
	}

	/**
	 * Forgot password user not found exception. : handles exception if During
	 * Forgot Password Recovery user put wrong email or user does not exist.
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(UserNotFoundException.class)
	public ModelAndView forgotPasswordUserNotFoundException(UserNotFoundException ex) {
		return this.handleRedirectForgotPassword(ex);
	}

	/**
	 * Confirm signup invalid credentials.
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	 * 
	 * @ExceptionHandler(RuntimeException.class) public ModelAndView
	 * genralRuntimeException(RuntimeException ex) { return this.handleRedirect(ex);
	 * }
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(BadCredentialsException.class)
	public ModelAndView confirmSignupInvalidCredentials(BadCredentialsException ex) {
		return this.handleRedirectToLogin(ex);
	}

	/**
	 * Confirm signup invalid password.
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(InvalidPasswordException.class)
	public ModelAndView confirmSignupInvalidPassword(InvalidPasswordException ex) {
		return this.handleRedirectToLogin(ex);
	}

	/**
	 * Unexpected challenge.
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView unexpectedChallenge(RuntimeException ex) {
		return this.handleRedirectToLogin(ex);
	}

	/**
	 * Http request method not supported exception.
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ModelAndView httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		return this.handleRedirectToLogin(ex);
	}

	/**
	 * Handles redirect view : Redirects To Home Page..
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	private ModelAndView handleRedirect(Exception ex) {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("error", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}

	/**
	 * Handle redirect To Login Page.
	 *
	 * @param ex the ex
	 * @return the model and view?error=true
	 */
	private ModelAndView handleRedirectToLogin(Exception ex) {
		ModelAndView mav = new ModelAndView("auth/login");
		mav.addObject("error", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}

	/**
	 * Handle redirect signup. : Redirects to SignUp Page.
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	private ModelAndView handleRedirectSignup(Exception ex) {
		ModelAndView mav = new ModelAndView("auth/signup");
		SignUpDTO signUpDTO = new SignUpDTO();
		mav.addObject("signUpDTO", signUpDTO);
		mav.addObject("error", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}

	/**
	 * Handle redirect forgot password. : Redirects to Forgot Password Page.
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	private ModelAndView handleRedirectForgotPassword(Exception ex) {
		ModelAndView mav = new ModelAndView("password/forgotPassword");
		ForgotPasswordDTO forgotPasswordDto = new ForgotPasswordDTO();
		mav.addObject("forgotPasswordDto", forgotPasswordDto);

		mav.addObject("error", ex.getMessage());

		ex.printStackTrace();
		return mav;
	}

}
