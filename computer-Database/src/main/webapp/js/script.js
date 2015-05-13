/**
 * 
 */
function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

$.validator.addMethod(
        "dateFormat",
        function(value, element) {
            var i = /\d{2}-\d{2}-\d{4}/;
            return i.test(value);
        });	

$(document).ready(function(){
	var x = readCookie("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE");
	if(x == "en"){
	$("#add").validate({
		rules:{
			name :{
				required : true,
				minlength : 2
			},
			introduced :{
				dateISO : true
			},
			discontinued :{
				dateISO : true
			}
		},
		messages :{
			introduced :"Please enter a valid date YYYY-MM-DD.",
			discontinued :"Please enter a valid date YYYY-MM-DD."
			
		}
	})
	}else if(x == "fr")
		$("#add").validate({
			rules:{
				name :{
					required : true,
					minlength : 2
				},
				introduced :{
					dateFormat : true
				},
				discontinued :{
					dateFormat : true
				}
			},
			messages :{
				name : "Entrer au moins deux caracteres.",
				introduced :"Entrer une date au format suivant DD-MM-YYYY.",
				discontinued :"Entrer une date au format suivant DD-MM-YYYY."
				
			}
		})
});
