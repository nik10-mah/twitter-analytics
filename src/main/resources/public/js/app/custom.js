/*It checks for status of Event if it's Running then Disables the Keyword Add/Remove
 */
//$('.inputKeyword1 input').attr('disabled', 'disabled');
var stat = document.getElementById("status").value;
var inputKeyword = document.getElementById("inputKeyword1");
if (stat == "RUNNING") {
	inputKeyword.disabled = true;
	$(document).ready(function() {
		$("div.bootstrap-tagsinput").addClass("disable-div");
	});
}
