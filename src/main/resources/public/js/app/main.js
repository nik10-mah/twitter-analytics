$(document).ready(function(){
	console.log('Ready');
	//alert('Test');
});

var Common = {
	comingSoon : function() {
		alert('Coming Soon');
	}
}

var Analyze = {
		onChangeDorpDown: function (){
			// Empty TextField
			document.getElementById("aSql").value = '';
			// Put value that is selected in DropDown
			document.getElementById("aSql").value = document.getElementById("selectedSql").value;
		},
		onKeyUpAnalyzeQuery: function (){
			// Deselect DropDown
			document.getElementById("selectedSql").value='';
		},
	}

var Collect = {
		clickTranslate: function (e){
			e.preventDefault;
			let sourceLanguage = 'en';
			// Get Checkboxes
			const cboxes = document.getElementsByName('targetLangs');
			// get total number of check boxes
		    const len = cboxes.length;
		    // array for selected checkboxes
		    let targetLangs = [];
		    // Get Only Selected Checkboxes
		    for (let i=0; i<len; i++) {
		    	if(cboxes[i].checked){
		    		// push in selected checkboxes array
		    		targetLangs.push(cboxes[i].value);
		    		//alert(i + (cboxes[i].checked?' checked ':' unchecked ') + cboxes[i].value);
		    	}
		    }
		    // Keywords to translate
		    const textToTranslate = document.getElementById("inputKeyword1").value;
		    
		    var request = $.ajax({
		    	  url: "/collect/startEvent/transalate?sourceLanguage="+sourceLanguage+"&targetLangs="+targetLangs+"&text="+textToTranslate,
		    	  type: "GET",
		    	  success: function (response){
		    		  // update UI
		    		 $('#translatedDiv').html(response);
		    	  },
		    	  error: function (errorResponse){
		    		  
		    	  }
		    	});
		    
		    
		}
}