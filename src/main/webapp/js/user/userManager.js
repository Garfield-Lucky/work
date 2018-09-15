 var offset = 0;//控制偏移多少条数据
 var dataNum=0 ;//记录数据库返回的数据总数
 var flag = 0;
 var page = 0;
 var pageSize = 5;//控制每页显示多少条数据
 
$(function(){
	 $(".query").find('.find').click(function(){
		 var userName =  $('.userName').val();
		 //还原偏移量
		 offset = 0;
		 page = 0;

		 $.ajax({
				type : "POST",
				url : "user/findUserList",
				async:false,
				data : {
					'userName':userName,
					'page':page,
					'pageSize':pageSize
				},
				dataType : 'json',
				success : function(data) {
					if (data.status == "0000") {
						var userList = Handlebars.compile($("#userList").html());
						$('#user_list').html(userList(data.list));
						dataNum = data.dataNum;
						if(dataNum != 0)
						{
						   $('.pagePosition').show();
						}else{
							  $('.pagePosition').hide();
						}
						$('#currentPage').html(data.currentPage);
						$('#countPage').html(data.countPage);
						$('.userName').attr('data-name',userName);
						deleteUser();
					}else{
						alert('获取失败');
					}  
				},
				error : function() {
					alert("error");
				}
			});
		 
 	     });
	 
	 $("#session1").on('click','.detail',function(){
		 var workNum =   $(this).attr('data-id');
 		 $('.mainbody_right').load("user/detail.do",{userName:workNum});
 	     });
	 $(".query").find('.addUser').click(function(){
 		 $('.mainbody_right').load("pages/addUserInfo.jsp");
 	     });
	 
	 userList();
	 
	 //首页
	 $("#session1").on("click", "#firstPage",  function() {
		    pageA(0,0);
			var userName =  $('.userName').attr('data-name');
		    $.ajax({
				type : "POST",
				url : "user/findUserList",
				async:false,
				data : {
					'userName':userName,
                    'page':page,
					'pageSize':pageSize
				},
				dataType : 'json',
				success : function(data) {
					if (data.status == "0000") {
						var userList = Handlebars.compile($("#userList").html());
						$('#user_list').html(userList(data.list));
						dataNum = data.dataNum;
						if(dataNum != 0)
						{
						   $('.pagePosition').show();
						}else{
							  $('.pagePosition').hide();
						}
						$('#currentPage').html(data.currentPage);
						$('#countPage').html(data.countPage);
						deleteUser();
					}else{
						alert('获取失败');
					}  
				},
				error : function() {
					alert("error");
				}
			});
			 
		});
	 //上一页
	 $("#session1").on("click", "#prevPage",  function() {
		    pageA(2,0);
			var userName =  $('.userName').attr('data-name');
		    $.ajax({
				type : "POST",
				url : "user/findUserList",
				async:false,
				data : {
					'userName':userName,
					'page':page,
					'pageSize':pageSize
				},
				dataType : 'json',
				success : function(data) {
					if (data.status == "0000") {
						var userList = Handlebars.compile($("#userList").html());
						$('#user_list').html(userList(data.list));
						dataNum = data.dataNum;
						if(dataNum != 0)
						{
						   $('.pagePosition').show();
						}else{
							  $('.pagePosition').hide();
						}
						$('#currentPage').html(data.currentPage);
						$('#countPage').html(data.countPage);
						deleteUser();
					}else{
						alert('获取失败');
					}  
				},
				error : function() {
					alert("error");
				}
			});
			 
		});
	 //下一页
	 $("#session1").on("click", "#nextPage",  function() {
		 pageA(1,0);
			var userName =  $('.userName').attr('data-name');
		    $.ajax({
				type : "POST",
				url : "user/findUserList",
				async:false,
				data : {
					'userName':userName,
					'page':page,
					'pageSize':pageSize
				},
				dataType : 'json',
				success : function(data) {
					if (data.status == "0000") {
						var userList = Handlebars.compile($("#userList").html());
						$('#user_list').html(userList(data.list));
						dataNum = data.dataNum;
						if(dataNum != 0)
						{
						   $('.pagePosition').show();
						}else{
							  $('.pagePosition').hide();
						}
						$('#currentPage').html(data.currentPage);
						$('#countPage').html(data.countPage);
						deleteUser();
					}else{
						alert('获取失败');
					}  
				},
				error : function() {
					alert("error");
				}
			});
			 
		});
	 //尾页
	 $("#session1").on("click", "#trailPage",  function() {
		    pageA(3,0);
			var userName =  $('.userName').attr('data-name');
		    $.ajax({
				type : "POST",
				url : "user/findUserList",
				async:false,
				data : {
					'userName':userName,
					'page':page,
					'pageSize':pageSize
				},
				dataType : 'json',
				success : function(data) {
					if (data.status == "0000") {
						var userList = Handlebars.compile($("#userList").html());
						$('#user_list').html(userList(data.list));
						dataNum = data.dataNum;
						if(dataNum != 0)
						{
						   $('.pagePosition').show();
						}else{
							  $('.pagePosition').hide();
						}
						$('#currentPage').html(data.currentPage);
						$('#countPage').html(data.countPage);
						deleteUser();
					}else{
						alert('获取失败');
					}  
				},
				error : function() {
					alert("error");
				}
			});
			 
		});
	 //跳页
	 $("#session1").on("click", "#query",  function() {
		    var pageTo = $("#pageNum").val();
		    pageA(4,pageTo);
			var userName =  $('.userName').attr('data-name');
		    $.ajax({
				type : "POST",
				url : "user/findUserList",
				async:false,
				data : {
					'userName':userName,
					'page':page,
					'pageSize':pageSize
				},
				dataType : 'json',
				success : function(data) {
					if (data.status == "0000") {
						var userList = Handlebars.compile($("#userList").html());
						$('#user_list').html(userList(data.list));
						dataNum = data.dataNum;
						if(dataNum != 0)
						{
						   $('.pagePosition').show();
						}else{
							  $('.pagePosition').hide();
						}
						$('#currentPage').html(data.currentPage);
						$('#countPage').html(data.countPage);
						deleteUser();
					}else{
						alert('获取失败');
					}  
				},
				error : function() {
					alert("error");
				}
			});
			 
		});
 });

//分页
function pageA(state,pageTo){
   var t1=dataNum%pageSize;  //求余
   var t2=parseInt(dataNum/pageSize); //总共有多少页 0代表一页，1代表两页
   if(t1 != 0){
	    t2=t2+1;
	 }
	   switch(state)
	   {
		      case 0:
		    	     offset = 0;
		    	  break;
		      case 1:
		    	  if(((offset) < (dataNum - dataNum%pageSize) && dataNum%pageSize != 0)||((offset+pageSize)<(dataNum - dataNum%pageSize) && dataNum%pageSize == 0))
		    	  {
		    	     offset += pageSize;
		    	  }else{
		    		  alert('到尾页了');
		    	  }
		    	  break;
		      case 2:
		    	  if(offset > 0)
		    	  {
		    		offset -= pageSize;
		    	  }else{
		    		offset = 0;
		    		alert('到首页了');
		    	  }
		    	  break;
		      case 3:
				     offset = (t2-1)*pageSize;
		    	  break;
		      case 4:
		    	  if(pageTo == null || pageTo == -1)
		    	  break;
	 	  
	 		  if(pageTo <= t2)
	 		  {
	 		    offset = (pageTo-1)*pageSize;
	 		  }else{
				offset = (t2-1)*pageSize;//尾页
	 		  }
		    	  break;
		      default:break;
	   }
	   page = offset; //该page表示偏移量
	 
}

//进入页面后自动调用此方法去后台查询数据
 function userList() {
		$.ajax({
			type : "POST",
			url : "user/findUserList",
			async:false,
			data : {
				'page':page,
				'pageSize':pageSize
			},
			dataType : 'json',
			success : function(data) {
				if (data.status == "0000") {
					var userList = Handlebars.compile($("#userList").html());
					$('#user_list').html(userList(data.list));
					dataNum = data.dataNum;
					if(dataNum != 0)
					{
					   $('.pagePosition').show();
					}else{
						  $('.pagePosition').hide();
					}
					$('#currentPage').html(data.currentPage);
					$('#countPage').html(data.countPage);
					deleteUser();
				}else{
					alert('获取失败');
				}  
			},
			error : function() {
				alert("error");
			}
		});
		
	}
 
 function deleteUser() {
		//删除用户
		$(".del").on("click",function() {
			var id = $(this).attr('data-id');
			var b = window.confirm('你确定要删除该用户吗？');
			if(b)
			{
			$.ajax({
				url:"user/delUser.do",//指定路径
				dataType:"json",
				type:"POST",
				data:{
					'id':id,
					'page':page,
					'pageSize':pageSize
				},cache:false,
				success:function(data){	
					var userList = Handlebars.compile($("#userList").html());
					if(data.status=="0000"){
						$('#user_list').html(userList(data.list));
						dataNum = data.dataNum;
						if(dataNum != 0)
						{
						   $('.pagePosition').show();
						}else{
							  $('.pagePosition').hide();
						}
						$('#currentPage').html(data.currentPage);
						$('#countPage').html(data.countPage);
						deleteUser();
					}else{
						alert('删除失败');
					}
					
				},error : function(data) {
					alert('删除异常')
				}
			});	
		  }
		})
	}
 
