$("#forgotPasswordSubmit").attr('disabled', true);
$("#username").attr('disabled', true);

var resetPassword = {

	// function for validating password for reset the password.
	validatePassword : function(e) {
		var password = $("#newPassword").val();
		var confirm_password = $("#confirmPassword").val();
         //compare password 
		if (password != confirm_password) {
			$(".errorMsg").text('Passwords Do Not Match.');
			$(".errorMsg").css('color', 'red');
			$("#forgotPasswordSubmit").attr('disabled', true);
			return false;
		} else {
			$(".errorMsg").text('');
			$('.confirmPassword').css('border-color', '');
			$("#forgotPasswordSubmit").attr('disabled', false);

		}
		return true;
	}
	
}