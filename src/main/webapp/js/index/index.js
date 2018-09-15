 $(function(){
	 
	 $(".nav").find('.login').click(function(){
	 	  
		 window.location.href="login.jsp";
 	});
	 $(".nav").find('.logout').click(function(){
	 	  
		 window.location.href="/loginOut";
 	});

	
    //用户管理 使用PageHelper展示
    $(".mainbody").find('.userManager').click(function(){

        // window.location.href="/loginOut";
        $('.mainbody_right').load("user/userList");
 	});

     //用户管理 使用handlebars展示
     $(".mainbody").find('.userManager2').click(function(){

         // window.location.href="/loginOut";
         $('.mainbody_right').load("/user/userManager");
     });

     //在线脑图（百度脑图）
     $(".mainbody").find('.kityMind').click(function(){

         $('.mainbody_right').load("/kityMind/addKityMind");
     });

     //文件上传
     $(".mainbody").find('.fileUpload').click(function(){

         $('.mainbody_right').load("/file/");
     });

    
 });

 function sessionCheck(path){
	 $.ajax({
			type : "POST",
			url : "filter/sessionCheck.do",
			async:false,
			data : {
			},
			dataType : 'json',
			success : function(data) {
				if (data.status == "0000") {
					$('.mainbody_right').load(path);
				}else{
					window.location.href="login.jsp";
				}
			},
			error : function() {
				alert("error");
			}
		});
 }