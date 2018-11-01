/**
 * 
 */
package com.insonix.athenapoc.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.athena.model.InvalidRequestException;

/**
 * The Class ErrorController: For handling all the exception globally
 * Any exception occured will redirect user to Home Page
 *
 * @author Nikhil Mahajan
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
	 * Handles redirect view .
	 *
	 * @param ex the ex
	 * @return the model and view
	 */
	private ModelAndView handleRedirect(Exception ex) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("error", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}
}
