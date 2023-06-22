package com.ml.epic.ta.dto;

public class SignUpDTO {

	String username;
	// boolean invitation;
	// List<String> invitationType = new ArrayList<String>();
	// String tempPassword;
	String phoneNumber;
	String makePhNoVerifired;
	String email;
	String makeEmailVerified;

	public SignUpDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignUpDTO(String username) {
		super();
		this.username = username;
	}

	public SignUpDTO(String username, String phoneNumber, String makePhNoVerifired, String email,
			String makeEmailVerified) {
		super();
		this.username = username;
		// this.invitation = invitation;
		// this.invitationType = invitationType;
		// this.tempPassword = tempPassword;
		this.phoneNumber = phoneNumber;
		this.makePhNoVerifired = makePhNoVerifired;
		this.email = email;
		this.makeEmailVerified = makeEmailVerified;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * public boolean isInvitation() { return invitation; }
	 * 
	 * 
	 * public void setInvitation(boolean invitation) { this.invitation = invitation;
	 * }
	 * 
	 * 
	 * public List<String> getInvitationType() { return invitationType; }
	 * 
	 * 
	 * public void setInvitationType(List<String> invitationType) {
	 * this.invitationType = invitationType; }
	 */

	/*
	 * public String getTempPassword() { return tempPassword; }
	 * 
	 * 
	 * public void setTempPassword(String tempPassword) { this.tempPassword =
	 * tempPassword; }
	 */

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMakePhNoVerifired() {
		return makePhNoVerifired;
	}

	public void setMakePhNoVerifired(String makePhNoVerifired) {
		this.makePhNoVerifired = makePhNoVerifired;
	}

	public String getMakeEmailVerified() {
		return makeEmailVerified;
	}

	public void setMakeEmailVerified(String makeEmailVerified) {
		this.makeEmailVerified = makeEmailVerified;
	}

}
