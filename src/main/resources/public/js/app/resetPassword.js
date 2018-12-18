$("#resetPasswordSubmit").attr('disabled', true);
var resetPassword = {

	// function for validating password for reset the password.
	validatePassword : function(e) {
		var password = document.getElementById("password").value;
		var confirm_password = document.getElementById("confirmPassword").value;
         //compare password 
		if (password != confirm_password) {
			$(".errorMsg").text('Passwords Do Not Match.');
			$(".errorMsg").css('color', 'red');
			$("#resetPasswordSubmit").attr('disabled', true);
			return false;
		} else {
			$(".errorMsg").text('');
			$('.confirmPassword').css('border-color', '');
			$("#resetPasswordSubmit").attr('disabled', false);

		}
		return true;
	}
}