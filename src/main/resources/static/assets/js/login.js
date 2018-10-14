
jQuery(document).ready(function() {

    $('.login').click(function(){
        
    	login(); 
    });
    
    $(".page-container").keydown(function(event) {    
        if (event.keyCode == 13) {    
        	login();    
        }    
    })    
    $('.page-container form .userName, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });

});

 function login(){
	 var userName = $('.userName').val();
     var password = $('.password').val();
     var autoLogin = false;
     if(userName == '') {
         $('.error').fadeOut('fast', function(){
             $(this).css('top', '27px');
         });
         $('.error').fadeIn('fast', function(){
             $(this).parent().find('.userName').focus();
         });
         return false;
     }
     if(password == '') {
         $('.error').fadeOut('fast', function(){
             $(this).css('top', '96px');
         });
         $('.error').fadeIn('fast', function(){
             $(this).parent().find('.password').focus();
         });
         return false;
     }
     if ($('.autoLogin').is(':checked')) {
			 
     	autoLogin = true;
		}
     
     if(userName.trim() != '' && password.trim() != '')
     {
     $.ajax({
			type: "POST",
			url: 'login',
			data: {
				'userName':userName,
				'password':password,
                'autoLogin':autoLogin
			},
			dataType:'json',
			success : function(data) {
				alert('登录成功');
					},
				error : function() {
					alert("登录失败");
					}
				});
     }
 }
 