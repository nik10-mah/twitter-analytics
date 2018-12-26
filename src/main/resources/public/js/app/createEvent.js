
$(document).ready(function(){
	 
	
$("#msgBlock").css({'display':'none'});
	$("#saveBtn").click(function(e){
		  alert("The paragraph was clicked.");
		  e.preventDefault;
		  debugger;
		  var eventName = $("#eventName").val();
		  var keywords = $("#eventKeywords").val();
		  debugger;
		  var request = $.ajax({
				url : "/collect/createEvent/execute",
				type : "post",
				//contentType : "application/json",
				//dataType : 'json',
				//dataType: 'json',
				contentType: 'application/json',
				data: JSON.stringify({
						eventName: eventName,
						eventKeywords: keywords,
						status: "CREATED"}
				)
				,
				success : function(response) {
					// update UI
					// Empty Dialog Fields
					$("#eventKeywords").val('');
					$("#eventName").val('');
					// As we have used class bootstrap-tagsinput , it makes span for every keyword for making tags.
					// remove all tags.
					 $(".bootstrap-tagsinput").find("span").remove();
					 
					// close pop up
					 $('#createDialog').modal('toggle');
					$('#displayCollect').html(response);
					$('#displayMsg').text('Event Saved Successfully .')

					
				},
				error : function(errorResponse) {
				console.log(errorResponse);
				}
			});
		});
});
