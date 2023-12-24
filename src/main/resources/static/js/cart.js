$(document).ready(function() {
	
	
	
	$.ajax({
	        method: 'POST',
	        url: 'http://localhost:8080/qty',
	        dataType: "JSON",
	        data: {q:q, p:p },
	        success: function(data)
	        {
	          
	           console.log(data);
	          
	        }
	     });
	     
	     
	  });
