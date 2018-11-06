$(document).ready(function(){
	console.log('Ready');
//	alert('Test');
});

var Common = {
	comingSoon : function() {
		alert('Coming Soon');
	}
}

var Analyze = {
		onChangeDorpDown: function (){
			document.getElementById("aSql").value = '';
			document.getElementById("aSql").value = document.getElementById("selectedSql").value;
		},
		onKeyUpAnalyzeQuery: function (){
			document.getElementById("selectedSql").value='';
		},
	}