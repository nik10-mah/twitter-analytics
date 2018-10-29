/**
 * 
 */
package com.insonix.athenapoc.service;

import java.util.Map;

/**
 * @author Nikhil Mahajan
 * @since Oct 27, 2018
 */
public interface AthenaService {

	Map<String, Object> executeQuery(String query) throws InterruptedException; 
}
