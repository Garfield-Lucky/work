<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- tab -->
<div class="content layui-tab layui-tab-card" lay-filter="mytab" lay-allowclose="true" style="width: 100%;height: 100%;">
		  <ul class="layui-tab-title" style="border-color: #FFB800;">
		    <li class="layui-this" lay-id="0" style="border-style: none;"><span>用户管理</span></li>
		  </ul>
		  <div class="layui-tab-content" style="height: 100%;color: black;padding: 0px;">
<!--    		      所有新打开的tab都放在下面这个div里面 -->
   		      <div class="layui-tab-item layui-show">
					<div class="content">
						<div class="demoTable" style="padding:5px;color: white;">
							姓名：
							<div class="layui-inline">
								<input class="layui-input" type="text" name="userName" id="userName" value="" autocomplete="off">
							</div>
							电话：
							<div class="layui-inline">
								<input class="layui-input" type="text" name="tel" id="tel" value="" autocomplete="off">
							</div>
							<button class="layui-btn" id="reload" data-type="reload">搜索</button>
						</div>
						
						<xblock>
						<button class="layui-btn layui-btn-sm add">
							<i class="layui-icon">&#xe608;</i>添加用户
						</button>

						<button class="layui-btn layui-btn-sm refresh">
							<i class="layui-icon">&#xe669;</i>刷新
						</button>
						</xblock> 
						<table class="layui-hide" id="table_content" lay-filter="tableId"></table>
						<!-- 右侧内容框架，更改从这里结束 -->
				</div>
   		     </div>
         </div>
</div>	
<iframe id="downloadFrame" width="0" height="0"></iframe>			   			
	
<script>
		layui.use('laydate', function(){
            var laydate = layui.laydate;
            //执行一个laydate实例
            laydate.render({
                elem: '#createTime' //指定元素
            });
        });
		layui.use(['layer','element','table'],
			function() {
				var table = layui.table; //表格
				var element = layui.element;
				var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
				//渲染
				table.render({
							elem : '#table_content',
							method : "post",
							url : '${pageContext.request.contextPath}/user/findUserList'
							,id: 'tableId'
							,page : true //是否显示分页
							,limit:10
							,limits:[10,20,30,40,50]
							,where :{}
							/* ,toolbar: '#barDemo'  */
							,cols : [ [ 
							   {
								title : '操作',
								width: 150,
								templet:function(row){
									var html='';
										html+='<a href="javaScript:viewData('+ row.id +');" title="查看"><i class="layui-icon">&#xe615;</i></a>&nbsp;&nbsp;';
										html+='<a href="javaScript:modiData('+ row.id +');" title="修改"><i class="layui-icon">&#xe642;</i></a>&nbsp;&nbsp;';
										html+='<a href="javaScript:delOneData('+ row.id +');" title="删除"><i class="layui-icon">&#xe640;</i></a>&nbsp;&nbsp;';
								    return html;
								}
							}, {
								field : 'id',
								title : 'ID',
                                width : 100,
								sort : true
							}, {
								field : 'userName',
                                width : 100,
								title : '账号'
								
							}, {
								field : 'trueName',
                                width : 100,
								title : '真实姓名'
							}, {
								field : 'age',
                                width : 100,
								title : '年龄'
							}, {
								field : 'sex',
                                width : 100,
								title : '性别'
							}, {
								field : 'tel',
                                width : 100,
								title : '电话'
							}, {
								field : 'wx',
                                width : 100,
								title : '微信'
							}, {
								field : 'lastLoginTime',
                                width : 150,
								title : '最后登录时间'
						    }, {
								field : 'status',
                                width : 100,
								title : '状态',
							    templet:function(row){
								    if(row.status==0)
								        return '正常';
								    else
								        return '禁用';
								}

                            }

							] ],
							width: 1140,
						    done: function(res, curr, count){
						    	   //关闭加载动画
						    	    layer.close(index);
								    //如果是异步请求数据方式，res即为你接口返回的信息。
								    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
								    console.log(res);
								    //得到当前页码
								    console.log(curr); 
								    //得到数据总量
								    console.log(count);
								  
							  }
					  });

			 var active = {
					reload : function() {
						var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
						//执行重载
						table.reload('tableId', {
							page : {
								curr : 1
							//重新从第 1 页开始
							},
							where : {
								userName : $("#userName").val()

							},
							done: function(res, curr, count){
						    	   //关闭加载动画
						    	    layer.close(index);
							  }
						});
					}
				}; 
					
			 
			 //新增
			$(".add").on('click',function(){
			     var tabId=new Date().getTime();
				 var url = '${pageContext.request.contextPath}/user/add?tabId='+tabId;
				 addTab('添加用户',url,tabId);
			});
			 

			 //刷新
			$(".refresh").on('click',function(){
				var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
				//执行重载
				table.reload('tableId', {
					page : {
						curr : 1
					//重新从第 1 页开始
					},
					where : {
					},
					done: function(res, curr, count){
				    	   //关闭加载动画
				    	    layer.close(index);
					  }
				});
			});

		element.on('tab(mytab)', function(data){
			var sid = $('.layui-this').attr('lay-id');//选中的TAB
			if(sid==0){//当指定tab被选中时
				$('#reload').click();  //模拟点击查询，重新加载数据
			}
		});
		 
		 $('.demoTable .layui-btn').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});

	});

	//查看
	function viewData(id){
		 var tabId=new Date().getTime();
		 var url = '${pageContext.request.contextPath}/user/view.action?id='+id+'&tabId='+tabId;
		 addTab('详情',url,tabId);
	}			

	//编辑
	function modiData(id){
        var tabId=new Date().getTime();
		 var url = '${pageContext.request.contextPath}/user/edit.action?id='+id+'&tabId='+tabId;
		 addTab('编辑',url,tabId);
	}	
	//删除
	function delOneData(id){//删除单条数据
    	   //拿到table对象
    	   layui.use('table',function() {
				var table = layui.table; //表格
	    	    layer.confirm('确定要删除此数据吗?', function(index){ 
	              	$.ajax({
	             	   type: "POST",dataType: "json",
	             	   url: "user/delUser",
	             	   data : {
							id:id
						},
	             	   beforeSend:function(msg){
	             			layer.load(0, {shade: [0.1,'#fff'] });
	             	   },
	           		   success: function(msgJson){
	           			  layer.closeAll('loading');
	           		   		 if (!msgJson || !msgJson.length){
                                 if (msgJson.status && msgJson.status == "error" && msgJson.message){
	                             	 layer.msg(msgJson.message);
	                            	     return;
	                              }else if(msgJson.status=="success"){
	                             	 layer.msg(msgJson.message);
	                             	//执行重载
	                  				table.reload('tableId', {
	                  					page : {
	                  						curr : 1
	                  					//重新从第 1 页开始
	                  					},
	                  					where : {

	                  					}
	                  				});
	                              }
	                          } 
	          		   },
	          		   error: function(msg){
	          			  layer.closeAll('loading');
	          		   }
	             	});

              });
    	   
    	   });
       }
			

</script>