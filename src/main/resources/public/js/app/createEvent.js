$("#msgBlock").css({'display':'none'});
$("#saveBtn").attr("disabled",true);

var CreateEvent = {
			save: function (e){
				 	$("#saveBtn").attr("disabled",true);
				 
				 	e.preventDefault;
				 	let eventName = $("#eventName").val();
				 	let keywords = $("#eventKeywords").val();
				  
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
							 $("#eventKeywords").tagsinput('removeAll');

							// close pop up
							 $('#createDialog').modal('toggle');
							 
							$('#displayCollect').html(response);
							$('#displayMsg').text('Event Saved Successfully .')

							
						},
						error : function(errorResponse) {
						console.log(errorResponse);
						}
					});
			},
			onKeyUpEvt: function (){
				  let eventName = $("#eventName").val();
				  let keywords = $("#eventKeywords").val();
				  if(null !== eventName && '' !== eventName && undefined !== eventName
						  && null !== keywords && '' !== keywords && undefined !== keywords){
					  $("#saveBtn").attr("disabled",false);
				  }
				  else{
					  $("#saveBtn").attr("disabled",true);
				  }
			}
	}

/*window.attachEvent = function(){
    document.querySelector("input[data-role='tagsinput']").onkeyup(function(){
        CreateEvent.onKeyUpEvt();
        console.log("All resources finished loading!");

    });
};
window.addEventListener("load", attachEvent);*/

$(document).ready(function(){
	
});



