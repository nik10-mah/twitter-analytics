$(document).ready(function() {
            $('#eventList').DataTable();
 } ); 


var Listing = {
		deleteEvent: function (obj) {
			let id = $(obj).attr("data-id");
			 	var request = $.ajax({
					url : "/collect/deleteEvent/"+id,
					type : "DELETE",
					contentType: 'application/xml',
					
					success : function(response) {
						
						$('#displayCollect').html(response);
						$('#displayMsg').text('Event Deleted Successfully .');

						
					},
					error : function(errorResponse) {
					console.log(errorResponse);
					}
				});
		}
}
