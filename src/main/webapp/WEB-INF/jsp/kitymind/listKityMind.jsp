<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content layui-tab layui-tab-card" lay-filter="mytab" lay-allowclose="true" style="width: 100%;height: 100%;">
		  <ul class="layui-tab-title" style="border-color: #FFB800;">
		    <li class="layui-this" lay-id="0" style="border-style: none;"><span>在线脑图</span></li>
		  </ul>
		  <div class="layui-tab-content" style="height: 100%;color: black;padding: 0px;">
<!--    		      所有新打开的tab都放在下面这个div里面 -->
   		      <div class="layui-tab-item layui-show">
					<div class="content">
					<div class="demoTable" style="padding:5px;color: white;">
						创建人：
						<div class="layui-inline">
							<input class="layui-input" type="text" name="createUserName" id="createUserName"
								value="" autocomplete="off">
							  
						</div>
						脑图名称：
						<div class="layui-inline">
							<input class="layui-input" type="text" name="mindName" id=mindName"
								value="" autocomplete="off">
						</div>
						
						创建日期：
						<div class="layui-inline">
							<input class="layui-input" type="text" name="createTime" id="createTime"
								value="" autocomplete="off">
						</div>


						
						<button class="layui-btn" id="reload" data-type="reload">搜索</button>
					</div>
				
				<xblock>
				<button class="layui-btn layui-btn-sm add-form">
					<i class="layui-icon">&#xe608;</i>添加
				</button>
				</xblock> 
				<table class="layui-hide" id="table_content" lay-filter="tableId"></table>
				<!-- 右侧内容框架，更改从这里结束 -->
			</div>
		</div>
		<!-- 右侧主体结束 -->
		</div>
		</div>
		
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
							url : '${pageContext.request.contextPath}/kityMind/kityMindList'
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
							 		if(row.createUserName=='${user.userName }'){
									    html='<a href="javaScript:modiData(\''+ row.id +'\',\''+row.createUserName+'\');" title="修改"><i class="layui-icon" >&#xe642;</i></a>&nbsp;&nbsp;</a> '
										    html+='<a href="javaScript:deleteData(\''+ row.id +'\');" title="删除"><i class="layui-icon" >&#xe640;</i></a>&nbsp;&nbsp;</a>';
									        html+='<a href="javaScript:viewData(\''+ row.id +'\');" title="查看"><i class="layui-icon"  >&#xe615;</i></a>&nbsp;&nbsp; </a>';
							 			}else{
							 				 html+='<a href="javaScript:viewData(\''+ row.id +'\');" title="查看"><i class="layui-icon"  >&#xe615;</i></a>&nbsp;&nbsp;</a> ';
							 				 html+='<a href="javaScript:modiData(\''+ row.id +'\',\''+row.createUserName+'\');" title="另存为新脑图"><i class="layui-icon"  >&#xe61f;</i></a>&nbsp;&nbsp;</a>'
							 			}
									return html;
								}
							},  {
								field : 'mindName',
								title : '脑图名称',
								minWidth : 150,
							},  {
								field : 'createUserName',
								minWidth : 150,
								title : '创建人账号'
							}, {
								field : 'isOpen',
								minWidth : 150,
								title : '是否公开',
								templet: function(d){
									 if(d.isOpen==0)
									 {
										 return '公开';
									 }else{
										 return '私有';
									 }
								}
							}, {
								field : 'createTime',
								minWidth : 150,
								title : '填表日期'
							}
							] ],
						    done: function(res, curr, count){
						    	   //关闭加载动画
						    	    layer.close(index);
								  
							  }
					  });

			 var $ = layui.$, active = {
					reload : function() {
						var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
						//执行重载
						table.reload('tableId', {
							page : {
								curr : 1
							//重新从第 1 页开始
							},
							where : {

                                createUserName : $("#createUserName").val(),
								createTime : $("#createTime").val()
							},
							done: function(res, curr, count){
						    	   //关闭加载动画
						    	    layer.close(index);
							  }
						});
					}
				}; 
					
			 
			 //新增
			$(".add-form").on('click',function(){
                 var tabId=new Date().getTime();
				 var url = '${pageContext.request.contextPath}/kityMind/toAdd?tabId='+tabId;
				 addTab('新增脑图',url,tabId);
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
		
		 function modiData(ids,userName){//修改数据
			   var id=new Date().getTime();
			   var url= '${pageContext.request.contextPath}/kityMind/toEdit?&id='+ids+'&tabId='+id;
			   addTab('修改数据',url,id);
		   }
		 
		
		function viewData(ids){//查看数据
		       var tabId=new Date().getTime();
			   var url= '${pageContext.request.contextPath}/kityMind/view?id='+ids+'&tabId='+tabId;
			   addTab('脑图详情',url,tabId);
		   }
		 

	
	function deleteData(ids){//删除单条数据
        //拿到table对象
	    	    layer.confirm('确定要删除此数据吗?', function(index){
	    	        layer.close(index);
	              	$.ajax({
	             	   type: "POST",dataType: "json",
		         	   url: "${pageContext.request.contextPath}/kityMind/delete",
	             	   data : {
							id:ids
						},
	             	   beforeSend:function(msg){
	             			layer.load(0, {shade: [0.1,'#fff'] });
	             	   },
	           		   success: function(data){
	           			  layer.closeAll('loading');
                           if(data!=null&&data.code=='0')
                           {
                               top.layui.layer.msg('删除成功!');
                               $('#reload').click();
                           }else{
                               top.layui.layer.msg('删除失败!');
                           }

	          		   },
	          		   error: function(msg){
	          			  layer.closeAll('loading');
	          		   }
	             	});

              });
    }
      
</script>
</body>
</html>
