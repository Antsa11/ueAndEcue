$("#register").on('click', function(){
                var password1 = $("#password1").val();
                var password2 = $("#password2").val();
                var form = $('#registerForm');
                if(password1 != password2){
                    //$('#messageDiv').css("display","none");
                    alert("Mot de passe incorrect! \n Retapez votre mot de passe");
                    return;
                }
                               
             
                else
				{
						
					

						$.ajax({
						type: "post",
						url: "add_user",
						data: form.serialize(),
						success: function (data) {
						var result=data;
						//$('#content').html(result);
						 window.location.href = "login";

							}	
						});
                
					
				}
});



