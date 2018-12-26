package com.ml.epic.ta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.CodeDeliveryDetailsType;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordRequest;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.ForgotPasswordRequest;
import com.amazonaws.services.cognitoidp.model.ForgotPasswordResult;
import com.ml.epic.ta.dto.ForgotPasswordDTO;
import com.ml.epic.ta.service.PasswordService;

@Service("passwordService")
public class PasswordServiceImpl implements PasswordService {

	@Autowired
	AWSCognitoIdentityProvider authClient;

	/**
	 * Forget password init.: to Initiate the Forgot Password Recovery Process.
	 * Sends the OTP in email to user to change password.
	 *
	 * @param username the username
	 * @return the code delivery details type
	 */
	@Override
	public CodeDeliveryDetailsType forgetPasswordInit(String username) {
		ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest()
				.withClientId("3045rrdm5kjp5dh3qt85m9tkuu").withUsername(username);
		ForgotPasswordResult forgotPasswordResult = authClient.forgotPassword(forgotPasswordRequest);
		CodeDeliveryDetailsType codeDeliveryType = forgotPasswordResult.getCodeDeliveryDetails();
		return codeDeliveryType;
	}

	/**
	 * Forgot password execute.: takes ForgotPasswordDTO that includes otp,
	 * username, newPassword and resets the password.
	 *
	 * @param forgotPasswordDto the forgot password dto
	 * @return the confirm forgot password result
	 */
	@Override
	public ConfirmForgotPasswordResult forgotPasswordExecute(ForgotPasswordDTO forgotPasswordDto) {

		ConfirmForgotPasswordRequest confirmForgotPasswordRequest = new ConfirmForgotPasswordRequest()
				.withUsername(forgotPasswordDto.getUsername()).withConfirmationCode(forgotPasswordDto.getOtp())
				.withClientId("3045rrdm5kjp5dh3qt85m9tkuu").withPassword(forgotPasswordDto.getNewPassword());
		ConfirmForgotPasswordResult confirmForgotPasswordResult = authClient
				.confirmForgotPassword(confirmForgotPasswordRequest);
		return confirmForgotPasswordResult;

	}

}
