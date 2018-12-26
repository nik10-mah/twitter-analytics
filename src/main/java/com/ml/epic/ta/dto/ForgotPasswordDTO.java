package com.ml.epic.ta.dto;

public class ForgotPasswordDTO {

	String username = null;
	String otp = null;
	String newPassword = null;
	
	public ForgotPasswordDTO(String username, String otp, String newPassword) {
		super();
		this.username = username;
		this.otp = otp;
		this.newPassword = newPassword;
	}

	public ForgotPasswordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
