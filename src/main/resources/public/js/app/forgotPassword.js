$("#forgotPasswordSubmit").attr('disabled', true);
var forgotPassword = {

	onKeyUpUsername : function(e) {
		let username = $("#username").val();

		if (username != null && username != "" && username != undefined) {
			$("#forgotPasswordSubmit").attr('disabled', false);
		} else {
			$("#forgotPasswordSubmit").attr('disabled', true);
		}
	}
}