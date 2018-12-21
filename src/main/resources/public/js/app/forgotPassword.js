$("#forgotPasswordSubmit").attr('disabled', true);
var forgotPassword = {

	/*// function for validating password for reset the password.
	validatePassword : function(e) {
		var password = document.getElementById("password").value;
		var confirm_password = document.getElementById("confirmPassword").value;
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
	}*/
		
		onKeyUpUsername: function (e){
			let username = 	$("#username").val();
			
			if(username != null && username != "" && username != undefined){
				$("#forgotPasswordSubmit").attr('disabled', false);
			}
			else{
				$("#forgotPasswordSubmit").attr('disabled', true);
			}
		}
}