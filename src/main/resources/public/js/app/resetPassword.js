$("#forgotPasswordSubmit").attr('disabled', true);
$("#username").prop("readonly", true);


var resetPassword = {

	// function for validating password for reset the password.
	validatePassword : function(e) {

		let password = $("#newPassword").val();
		let confirm_password = $("#confirmPassword").val();

		// compare password 
		if (password != confirm_password) {
			$(".errorMsg").text('Passwords Do Not Match.');
			$(".errorMsg").css('color', 'red');
			$("#forgotPasswordSubmit").attr('disabled', true);
			return false;
		} else {
			$(".errorMsg").text('');
			enableSubmitButton();
		}
		return true;

	},
	validateOTP : function(e) {
		let otp = $("#otp").val();
		if (otp == null || otp == "" || otp == undefined) {
			$(".otpErrorMsg").text('enter valid otp.');
			$(".otpErrorMsg").css('color', 'red');
			$("#forgotPasswordSubmit").attr('disabled', true);
		} else {
			$(".otpErrorMsg").text('');
			enableSubmitButton();

		}

	}

}

// Common Method to check Enable/Disable The Submit Button
function enableSubmitButton() {
	let password = $("#newPassword").val();
	let confirm_password = $("#confirmPassword").val();
	let otp = $("#otp").val();
	if (password !== null && password !== "" && password !== undefined
			&& confirm_password !== null && confirm_password !== ""
			&& confirm_password !== undefined && otp !== null && otp !== ""
			&& otp !== undefined) {

		$("#forgotPasswordSubmit").attr('disabled', false);

	} else {
		$("#forgotPasswordSubmit").attr('disabled', true);
	}
}