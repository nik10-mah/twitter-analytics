package com.ml.epic.ta.service;

import com.amazonaws.services.cognitoidp.model.CodeDeliveryDetailsType;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordResult;
import com.ml.epic.ta.dto.ForgotPasswordDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface PasswordService.
 */
public interface PasswordService {

	/**
	 * Forget password init.: to Initiate the Forgot Password Recovery Process.
	 * Sends the OTP in email to user to change password.
	 *
	 * @param username the username
	 * @return the code delivery details type
	 */
	CodeDeliveryDetailsType forgetPasswordInit(String username);

	/**
	 * Forgot password execute.: takes ForgotPasswordDTO that includes otp,
	 * username, newPassword and resets the password.
	 *
	 * @param forgotPasswordDto the forgot password dto
	 * @return the confirm forgot password result
	 */
	ConfirmForgotPasswordResult forgotPasswordExecute(ForgotPasswordDTO forgotPasswordDto);

}
