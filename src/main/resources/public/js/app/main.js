$(document).ready(function() {
	console.log('Ready');
	// alert('Test');
});

var Common = {
	comingSoon : function() {
		alert('Coming Soon');
	},
	// common function for fetching html list for a table. TODO:// to be tested 
	fetchData : function(url, tableId, callback){
		$.get(baseUrl + "/list", function(data, status) {
			// todo set datatable for all pages			
			var tableObj = $('#'+tableId);
			tableObj.parent().html(data);
			tableObj.dataTable();
			
			// $.noop is blank function. if callback is udefined then execute blank function
			callback = callback || $.noop;
			callback(data);
		});
	},
	
	// common functionn for submitting a form. TODO:// to be tested
	submitData : function(formObj,callback) {
		
		var url = $(formObj).attr("action");
		var formData = $(formObj).serialize();
		
		$.ajax({
			type : "POST",
			url : url,
			data : formData,
			success : function(response) {},
			error : function(XMLHttpRequest, textStatus,
					errorThrown) {
				alert("Oops some error has occurred");
				$(obj).find(':submit').prop('disabled', false);

			}
		})
	}
}






