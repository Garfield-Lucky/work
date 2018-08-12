<%@page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/LigerGrid-Tags" prefix="ligerTable"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>CDA脑图列表</title>
    <link href="${pageContext.request.contextPath}/css/control.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/script/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/script/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/script/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/script/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/script/lhgdialog/lhgcore.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/script/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/script/common_public.js"	type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/script/common_list_page.js"  type="text/javascript"></script>

    <script type="text/javascript">
        $(function(){
			//menu
            $("#toptoolbar").ligerToolBar({ 
                items: [
                         { text: '显示查询', click: showQuery ,icon:'search' },
                         { line:true },
                         { text: '新增脑图', click: addMind,icon:'add'}
                         
                        ]
              });
			
        });
    </script>

	<script type="text/javascript">
	
	function actionModi(row){
		var html='';
 		if(row.CREATE_USER_CODE=='${UserSession.userName }'){
		    html='<a href="javaScript:modiData(\''+ row.ID +'\',\''+row.CREATE_USER_CODE+'\');" title="修改">修改</a>| '
			    html+='<a href="javaScript:deleteData(\''+ row.ID +'\');" title="删除">删除</a>|';
		        html+='<a href="javaScript:viewData(\''+ row.ID +'\');" title="查看">查看| </a>';
		        html+='<a href="javaScript:reName(\''+ row.ID +'\');" title="重命名">重命名</a>';
 			}else{
 				 html+='<a href="javaScript:viewData(\''+ row.ID +'\');" title="查看">查看</a>| ';
 				 html+='<a href="javaScript:modiData(\''+ row.ID +'\',\''+row.CREATE_USER_CODE+'\');" title="另存为新脑图">另存为新脑图</a>'
 			}
		return html;
	}
	
	 function modiData(id,userName){//修改数据
		   var tabId = <%=System.currentTimeMillis()%>;
		   var url= '${pageContext.request.contextPath}/caseinfo/kityMind_toAlter.action?&id='+id+'&userName='+userName;
		   top.framework_addTab(tabId,'脑图详情',url);
	   }
	 
	 function isopen(row){
		 var html='';
		 if(row.IS_OPEN==0)
			 {
			 html='公开';
			 }else{
				 html='私有';
			 }
		 return html;
	 }
	 
	 function viewData(id){//查看数据
		   var tabId = <%=System.currentTimeMillis()%>;
		   var url= '${pageContext.request.contextPath}/caseinfo/kityMind_toView.action?&id='+id;
		   top.framework_addTab(tabId,'脑图详情',url);
	   }
	 
	 function reName(id){//查看数据
		 top.jQuery.ligerDialog.prompt('请输入脑图名称','', function (yes,value) {
				if(yes){
					 $.ajax({
				 			type : "POST",
				 			url : "${pageContext.request.contextPath}/caseinfo/kityMind_reName.action",
				 			async:false,
				 			data : {'id':id,'mindName':value},
				 			dataType : 'json',
				 			success : function(data) {
				 				if(data!=null&&data.Type=='Y')
			 					{
				 					//刷新前一个页面，并关闭当前页面
				 			       var jsonMessage = top.jQuery.ligerDialog.waitting("修改成功"); 
		                 		   setTimeout(function () { 
		                 		   jsonMessage.close();
		                 		   top.tab.reload('icoMenuToolAddTab_27');
		                     		}, 1000);
			 					}else{
			 						alert('修改失败!')
			 					}
				 			},
				 			error : function() {
				 				alert("error");
				 			}
				 		});
				}
		 });
	 }

		
		function deleteData(id){//删除单条数据
	        $.ligerDialog.confirm('你真的确认要删除此数据吗', function (yes) {
	        if(yes)
	        	$.ajax({
	         	   type: "POST",dataType: "json",
	         	   url: "${pageContext.request.contextPath}/caseinfo/kityMind_delete.action",
	         	   data: "id="+id,
	         	   beforeSend:function(msg){
	         		   $.ligerDialog.waitting('<img src="/gzxjcda/images/loading.gif">正在提交中,请稍候...');
	         	   },
	       		   success: function(msgJson){
	         		  $.ligerDialog.closeWaitting();
	          		   		 if (!msgJson || !msgJson.length){
	                             if (msgJson.Type && msgJson.Type == "error" && msgJson.Message){
	                            	 $.ligerDialog.error(msgJson.Message);
	                           	     return;
	                             }else if(msgJson.Type=="Y"){
	                            	 var msgManager = $.ligerDialog.waitting(msgJson.Message);
	                			      //刷新数据并关闭窗口
	                            	 setTimeout(function () {msgManager.close();
	                            	 grid.loadData();}, 1000); 
	                             }
	                         } 
	      		   },
	      		   error: function(msg){
	      			   $.ligerDialog.closeWaitting();
	      		   }
	         	});
	        });
	    }
	
// 新建脑图
   function addMind(){
	var cdaNo = '${param.cdaNo}';
     var tabId = <%=System.currentTimeMillis()%>;
     var url='${pageContext.request.contextPath}/caseinfo/kityMind_toAdd.action?&cdaNo='+cdaNo+'&tabId='+tabId;
// 	  window.open(url);
     top.framework_addTab(tabId,'新建脑图',url);
	}

	</script>

<ligerTable:table  divId="mainGrid" pageSize="25" queryUrl="/caseinfo/kityMind_querygrid.action" height="93%"
parms="$('#InfoQuery').serializeArray()" pageloadingId="pageloading" showDataTimeId="showDataTime" isScroll="true">
<%-- 	<ligerTable:column name="CDA_NO" display="CDA编号" width="150" ></ligerTable:column> --%>
<%-- 	<ligerTable:column name="AJBH_CLUE_NO" display="案件线索编号" width="180" ></ligerTable:column> --%>
	<ligerTable:column name="MIND_NAME" display="脑图名称" width="150" ></ligerTable:column>
	<ligerTable:column name="CREATE_USER_CODE" display="创建人账号" width="150" ></ligerTable:column>
	<ligerTable:column name="CREATE_USER_NAME" display="创建人姓名" width="150"></ligerTable:column>
	<ligerTable:column name="CREATE_USER_NAME" display="公开/私有" width="150" render="isopen"></ligerTable:column>
	<ligerTable:column name="CREATE_TIME" display="创建时间" width="150" ></ligerTable:column>
    <ligerTable:column name="actionModi" display="操作" width="280" render="actionModi"></ligerTable:column>
</ligerTable:table>


</head>
<body style="padding:6px; ">
<div id="queryContent">
<img src="${pageContext.request.contextPath}/plugin/naotu/cda_online_mind_readme.png" alt="CDA脑图在线说明" />
<form id="InfoQuery" method="post">
<input name="cdaNo" value="${param.cdaNo}" id="cdaNo" type="hidden" >
<input type="hidden" id="ajbh" name="ajbh" value="${param.ajbh }" />
			<table class="searchContent">
<!-- 				<tr> -->
<!-- 				<td><button type="submit" >检索</button></td> -->
<!-- 				</tr> -->
 
			</table>


</form>
</div>
<br/>
<div title="数据区">
	<div class="l-loading" style="display:block" id="pageloading"></div> 
	<div id="toptoolbar" style="width: 100%;"></div> 
	 
    <!-- 数据显示 -->
    <div id="mainGrid" style="margin:0; padding:0"></div>

    <div style="display:none;"></div>
    <div id="showDataTime" style="width: 100%;"></div>
</div>
</body>
</html>
