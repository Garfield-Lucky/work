<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
 
<!DOCTYPE html>
<html>
   <head>
      <title>Easy Work</title>
      <base href="<%=basePath%>" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
      <link rel="stylesheet" type="text/css" href="css/index/index.css">
      <script src="js/jquery/jquery-1.9.1.min.js"></script>
      <script type="text/javascript" src="js/handlebars-v2.0.0.js"></script>
      <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
      <script src="js/index/index.js"></script>
      <link rel="stylesheet" type="text/css" href="css/common/common.css">
   </head>
   <body>
	   <div class="nav" style="background-image: url('img/index/nav.gif');">
	     <img class="icon" src="img/index/logo.png" title="easy首页"></img>
		  <ul class="nav_menu">
<!-- 			<li class="active"><a href="#">主页</a></li> -->
<!-- 			<li><a href="#">个性化</a></li> -->
<!-- 			<li><a href="#">公告</a></li> -->
			<li class="logout"><a title="退出系统"><span class="glyphicon glyphicon-off" style="font-size: 16px;color:#CC2921;"></span></a></li>
			<li class="loginUser" ><span class="glyphicon glyphicon-user user_icon" style="font-size: 16px;color:#3276B1;"></span><span>${userInfo.trueName}</span></li>
		</ul>
	   </div>
	   <div class="mainbody">
	      <div class="mainbody_left panel-group" id="according">
<!-- 	       使第一个模块距离顶部留点距离 -->
          <div class="Mtop one">
          </div><!-- one -->
	         <div class="panel panel-default">
			  <button  class="btn btn-primary location" data-toggle="collapse" 
			     data-parent="#according" data-target="#demo1">
		                  常用功能
	           </button> 
	           <br>
	           <div id="demo1" class="collapse">
		           <ul>
				        <li >
				            <a class="register_info">登记信息</a>
				        </li>
				   </ul>
	          </div>
	        </div>


			  <shiro:hasRole name="admin">
	           <div class="panel panel-default">
	           <button  class="btn btn-primary location" data-toggle="collapse" 
			     data-target="#demo2" data-parent="#according">
		                  系统管理处 
	           </button> 
	           <br>
	           <div id="demo2" class="collapse">
		           <ul>

					   <li >
				            <a class="userManager">用户信息管理</a>
				        </li>
					   <li >
						   <a class="userManager2">用户信息管理2</a>
					   </li>
				   </ul>
	          </div>
	          </div>
			  </shiro:hasRole>

	      <div class="panel panel-default">
	          <button  class="btn btn-primary location" data-toggle="collapse" 
			     data-target="#demo3" data-parent="#according">
		                  个人中心 
	           </button> 
	           <br>
	           <div id="demo3" class="collapse">
		           <ul>
					   <shiro:hasPermission name="user:edit">
		                <li >
				            <a class="personInfo">个人信息</a>
				        </li>
					   </shiro:hasPermission>
				        <li >
				            <a class="accountManager">修改密码</a>
				        </li>
				   </ul>
	          </div>
	      </div>
	      </div>
	      <div class="mainbody_right">
	      
	      </div>
	  </div>
      
   </body>
</html>
