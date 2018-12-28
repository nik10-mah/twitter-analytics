$("#challengeSubmit").attr('disabled', true);

// Function for validating password for first time user.
var Challenge = {

	validatePassword : function(e) {
		var password = document.getElementById("password").value;
		var confirm_password = document.getElementById("confirmPassword").value;
               // condition to check the password.
		if (password != confirm_password) {
			$(".errorMsg").text('Passwords do not match.');
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