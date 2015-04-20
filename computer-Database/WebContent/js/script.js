/**
 * 
 */
$(document).ready(function(){
	$("#add").validate({
		rules:{
			computerName :{
				required : true,
				minlength : 2
			},
			introduced :{
				dateISO : true
			},
			d :{
				dateISO : true
			}
		},
		messages :{
			introduced :"Please enter a valid date YYYY-MM-DD.",
			discontinued :"Please enter a valid date YYYY-MM-DD."
			
		}
	})
});