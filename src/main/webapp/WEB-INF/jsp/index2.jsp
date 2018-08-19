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
    <title>首页</title>
    <base href="<%=basePath%>" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<h2>this is a index page11</h2>
<br>
<shiro:hasPermission name="user:list">
<a href="<%=path%>/userList">list</a>
</shiro:hasPermission>

<shiro:hasPermission name="user:add">
<a href="<%=path%>/add">add</a>
</shiro:hasPermission>

<shiro:hasAnyRoles name="admin,manager">
<a href="<%=path%>/edit">edit</a>
</shiro:hasAnyRoles>

<shiro:hasRole name="admin">
<a href="<%=path%>/del">del</a>
</shiro:hasRole>

<a href="<%=path%>/loginOut">退出</a>

</body>
</html>
