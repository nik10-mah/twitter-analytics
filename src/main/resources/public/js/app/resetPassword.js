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
			$("#challengeSubmit").attr('disabled', true);
			return false;
		} else {
			$(".errorMsg").text('');
			$('.confirmPassword').css('border-color', '');
			$("#challengeSubmit").attr('disabled', false);

		}
		return true;
	}
}