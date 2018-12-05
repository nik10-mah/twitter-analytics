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
		    
		    const result = this.checkValidation(targetLangs, textToTranslate);
		    if(!result){
		    	// if not valid
		    	return;
		    }
		   
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
		    
		    
		},
		checkValidation: function(targetLangs, textToTranslate){
			var valid = true;
			 // regex - No Special Character. Space and , is allowed
		    const regex = /[^a-zA-Z0-9 ,]/;
			 // Validation Select at least one Language Code.
		    if(targetLangs.length<1){
		    	alert('Kindly select at least one Language Code from checkboxes.')
		    	valid = false;
		    }		    
		    // check for empty value
		    else if(textToTranslate === null || textToTranslate === '' || textToTranslate === undefined){
		        alert('Kindly enter keywords.');
		    	valid = false;
		    }		   
		    // Validation for  No Special Character Space and , is allowed
		    else if(regex.test(textToTranslate)) {
		        alert('Kindly enter keywords without Special Characters.');
		    	valid = false;
		    }
		    else{
		    	valid = true;
		    }
		    
		    return valid;
		}
}


