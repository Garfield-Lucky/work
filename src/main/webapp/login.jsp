<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <base href="<%=basePath%>" />
        <meta charset="utf-8">
        <title>登录</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="page-container">
            <h1>Spring-Boot</h1>
            <form class="form" action="${pageContext.request.contextPath }/login" method="post">
                <input type="text" name="userName" class="userName" placeholder="用户名">
                <input type="password" name="password" class="password" placeholder="密码">
                <button type="submit" class="login1">登录</button>
                <div class="error"><span>+</span></div>
            </form>
             <div class="buttom">
			     <p class="autoLogin"><input type="checkbox" class="autoLogin" name="autoLogin">记住我</p>
			     <p class="live_pwd"><a>忘记密码</a></p>
			 </div>
         </div>
		
        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="assets/js/supersized-init.js"></script>
        <%--<script src="assets/js/login.js"></script>--%>

    </body>

</html>
