<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- tab -->
<div class="content layui-tab layui-tab-card" lay-filter="mytab" lay-allowclose="true" style="minWidth: 100%;height: 100%;">
		  <ul class="layui-tab-title" style="border-color: #FFB800;">
		    <li class="layui-this" lay-id="0" style="border-style: none;"><span>全文检索</span></li>
		  </ul>
		  <div class="layui-tab-content" style="height: 100%;color: black;padding: 0px;">
<!--    		      所有新打开的tab都放在下面这个div里面 -->
   		      <div class="layui-tab-item layui-show">
					<div class="content">
						<div class="demoTable" style="padding:5px;color: white;">
							查询内容：
							<div class="layui-inline">
								<input class="layui-input" type="text" name="queryContent" id="queryContent" value="" autocomplete="off">
							</div>
							<button class="layui-btn" id="reload" data-type="reload">搜索</button>
						</div>

						<xblock>
						<button class="layui-btn layui-btn-sm add">
							<i class="layui-icon">&#xe608;</i>添加索引
						</button>

						</xblock>
						<table class="layui-hide" id="table_content" lay-filter="tableId"></table>
						<!-- 右侧内容框架，更改从这里结束 -->
				</div>
   		     </div>
         </div>
</div>	
<iframe id="downloadFrame" minWidth="0" height="0"></iframe>			   			
	
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
							url : '${pageContext.request.contextPath}/elasticSearch/query'
							,id: 'tableId'
							,page : true //是否显示分页
							,limit:10
							,limits:[10,20,30,40,50]
							,where :{}
							/* ,toolbar: '#barDemo'  */
							,cols : [ [ 
							   {
								title : '操作',
								minWidth: 120,
								templet:function(row){
									var html='';
										html+='<a href="javaScript:modiData('+ row.id +');" title="修改"><i class="layui-icon">&#xe642;</i></a>&nbsp;&nbsp;';
										html+='<a href="javaScript:delOneData('+ row.id +');" title="删除"><i class="layui-icon">&#xe640;</i></a>&nbsp;&nbsp;';
								    return html;
								}
							}, {
								field : 'id',
								title : 'ID',
                                minWidth : 100,
								sort : true
							}, {
								field : 'name',
                                minWidth : 100,
								title : '姓名'
								
							}, {
								field : 'age',
                                minWidth : 100,
								title : '年龄'
							}, {
								field : 'sex',
                                minWidth : 100,
								title : '性别'
							}, {
								field : 'tel',
                                minWidth : 100,
								title : '电话'
							}, {
								field : 'description',
                                minWidth : 100,
								title : '描述'
							}, {
								field : 'createtm',
                                minWidth : 150,
								title : '创建时间'
						    }


							] ],
						    done: function(res, curr, count){
						    	   //关闭加载动画
						    	    layer.close(index);
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
                                queryContent : $("#queryContent").val()

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
				 var url = '${pageContext.request.contextPath}/elasticSearch/toAdd?tabId='+tabId;
				 addTab('添加索引',url,tabId);
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


	//编辑
	function modiData(id){
        var tabId=new Date().getTime();
		 var url = '${pageContext.request.contextPath}/elasticSearch/edit?id='+id+'&tabId='+tabId;
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
	             	   url: "elasticSearch/delete",
	             	   data : {
							id:id
						},
	             	   beforeSend:function(msg){
	             			layer.load(0, {shade: [0.1,'#fff'] });
	             	   },
	           		   success: function(data){
	           			  layer.closeAll('loading');
	           		   		 if (!data || !data.length){
                                 if (data.code && data.code == "1" && data.msg){
	                             	 layer.msg(data.msg);
	                            	     return;
	                              }else if(data.code=="0"){
	                             	 layer.msg(data.msg);
	                             	//执行重载
	                  				table.reload('tableId', {
	                  					page : {
	                  						curr : 1
	                  					//重新从第 1 页开始
	                  					},
	                  					where : {
                                            queryContent : $("#queryContent").val()
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