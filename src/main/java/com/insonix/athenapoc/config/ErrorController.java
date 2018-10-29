/**
 * 
 */
package com.insonix.athenapoc.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nikhil Mahajan
 * @since 29-Oct-2018
 *
 */
@ControllerAdvice
public class ErrorController {

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleNotFound(Exception ex) {
		ex.printStackTrace();
		return "index";
	}
}
