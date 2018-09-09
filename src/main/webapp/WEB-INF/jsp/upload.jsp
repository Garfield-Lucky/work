<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>文件上传</title>
    <base href="<%=basePath%>" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<form action="${pageContext.request.contextPath }/file/uploadFiles"
      method="post"
      enctype="multipart/form-data">
    文件1:<input type="file"  name="file"/>
    文件2:<input type="file"  name="file"/>
    <input type="submit" value="上传"/>
</form>

</body>
</html>
