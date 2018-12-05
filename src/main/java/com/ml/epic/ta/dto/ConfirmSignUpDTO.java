package com.ml.epic.ta.dto;

/**
 * The Class ConfirmSignUpDTO.
 */
public class ConfirmSignUpDTO {

	String email;
	
	String tempPassword;
	
	String newPassword;
	
	
	/**
	 * Instantiates a new confirm sign up DTO.
	 */
	public ConfirmSignUpDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new confirm sign up DTO.
	 *
	 * @param email the email
	 * @param tempPassword the temp password
	 * @param newPassword the new password
	 */
	public ConfirmSignUpDTO(String email, String tempPassword, String newPassword) {
		super();
		this.email = email;
		this.tempPassword = tempPassword;
		this.newPassword = newPassword;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the temp password.
	 *
	 * @return the temp password
	 */
	public String getTempPassword() {
		return tempPassword;
	}

	/**
	 * Sets the temp password.
	 *
	 * @param tempPassword the new temp password
	 */
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

	/**
	 * Gets the new password.
	 *
	 * @return the new password
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets the new password.
	 *
	 * @param newPassword the new new password
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfirmSignUpDTO [email=" + email + ", tempPassword=" + tempPassword + ", newPassword=" + newPassword
				+ "]";
	}
	
	
}
