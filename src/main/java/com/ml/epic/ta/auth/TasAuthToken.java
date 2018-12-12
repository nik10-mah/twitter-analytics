package com.ml.epic.ta.auth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class TasAuthToken extends UsernamePasswordAuthenticationToken {

	Map<?, ?> map = null;
	
	
	public TasAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		// TODO Auto-generated constructor stub
	}

	public TasAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Map<?,?> map) {
		super(principal, credentials, authorities);
		// TODO Auto-generated constructor stub
		this.map = map;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Map<?, ?> getMap() {
		return map;
	}

	public void setMap(Map<?,?> map) {
		this.map = map;
	}
	
	
	

}
