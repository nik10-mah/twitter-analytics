/**
 * 
 */
package com.ml.epic.ta.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.athena.model.InvalidRequestException;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.InvalidPasswordException;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.ml.epic.ta.dto.SignUpDTO;

/**
 * The Class ErrorController: For handling all the exception globally Any
 * exception occured will redirect user to Home Page
 *
 * 
 * @since 29-Oct-2018
 */
@ControllerAdvice
public class ErrorController {

	/**
	 * Error Handler for Athena API request exception for invlaid syntax
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
	 * Handle not found: genric handler for rest of the exceptions
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
		return this.handleRedirectSignup(ex);
	}

	/**
	 * Sign up. handler for exception if exception comes during signup
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(UsernameExistsException.class)
	public ModelAndView signUpUserAlreadyExist(UsernameExistsException ex) {
		return this.handleRedirectSignup(ex);
	}

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
		return this.handleRedirectConfirmSignup(ex);
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
		return this.handleRedirectConfirmSignup(ex);
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
		return this.handleRedirectConfirmSignup(ex);
	}

	/**
	 * Handles redirect view .
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
	 * Handle redirect confirm signup.
	 *
	 * @param ex the ex
	 * @return the model and view?error=true
	 */
	private ModelAndView handleRedirectConfirmSignup(Exception ex) {
		ModelAndView mav = new ModelAndView("login");
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
		ModelAndView mav = new ModelAndView("signup");
		SignUpDTO signUpDTO = new SignUpDTO();
		mav.addObject("signUpDTO", signUpDTO);
		mav.addObject("error", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}
}
