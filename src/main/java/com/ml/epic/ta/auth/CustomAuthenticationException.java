package com.ml.epic.ta.auth;

import java.util.Map;

import org.springframework.security.core.AuthenticationException;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomAuthenticationException.
 */
public class CustomAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 2935719187319083245L;

	 /** The default msg. */
 	static String defaultMsg = "";
	
	 /** The params. Map to hold multiple values */
 	Map<String, ?> params = null;
	
	 		
	/**
	 * Instantiates a new custom authentication exception.
	 */
	public CustomAuthenticationException() {
		super(defaultMsg);
	}
	
	/**
	 * Instantiates a new custom authentication exception.
	 *
	 * @param msg the msg
	 */
	public CustomAuthenticationException(String msg) {
		super(msg);
	}
	
	
	/**
	 * Instantiates a new custom authentication exception.
	 *
	 * @param params the params
	 */
	public CustomAuthenticationException(Map<String,?> params) {
		super((String)params.get("msg"));
		
		this.params = params;
	}
	
	
	
	/**
	 * Gets the default msg.
	 *
	 * @return the default msg
	 */
	public static String getDefaultMsg() {
		return defaultMsg;
	}
	
	/**
	 * Sets the default msg.
	 *
	 * @param defaultMsg the new default msg
	 */
	public static void setDefaultMsg(String defaultMsg) {
		CustomAuthenticationException.defaultMsg = defaultMsg;
	}
	
	
	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	public Map<String, ?> getParams() {
		return params;
	}
	
	/**
	 * Sets the params.
	 *
	 * @param params the params
	 */
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return "CustomAuthenticationException [params=" + params + "]";
	}
	
	

}
