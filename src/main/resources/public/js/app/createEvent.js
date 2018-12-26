$(document).ready(function(){
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
				dataType: 'json',
				contentType: 'application/json',
				data: JSON.stringify({
						eventName: eventName,
						eventKeywords: keywords,
						status: "CREATED"}
				)
				,
				success : function(response) {
					// update UI
					
					console.log(response);
				},
				error : function(errorResponse) {
				console.log(errorResponse);
				}
			});
		});
});
