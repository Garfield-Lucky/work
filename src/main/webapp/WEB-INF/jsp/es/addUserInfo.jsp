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
<html>
<head>
    <title>添加</title>
    <base href="<%=basePath%>" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index/xadmin.css?time=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css?time=<%=System.currentTimeMillis()%>">
<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/layui/layui.js?time=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/xadmin.js?time=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common_layui.js?time=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
<script type="text/javascript">
   var tabId = '${param.tabId}';
</script>
<style>
.layui-table .left{
    text-align:right;
    width:20%;
}
.layui-table .right{
    text-align:left;
    width:80%;
}
.layui-table input{
    width: 300px;
}
.layui-table input{
    width: 300px;
}
</style>
<body>
<form name="form" id="form" method="post" class="layui-form" action="${pageContext.request.contextPath }/elasticSearch/add" onsubmit="return ajaxSave(this);">
    <table class="layui-table" width="100%">
        <tr>
            <th colspan="2" align="left">添加索引</th>
        </tr>
        <tr>
            <td class="left" >ID:</td>
            <td class="right">
                <input type="text" id="id" name="id" class="layui-input" required />
            </td>
        </tr>
        <tr>
            <td class="left" >姓名:</td>
            <td class="right">
                <input type="text" id="name" name="name" class="layui-input" required />
            </td>
        </tr>
        <tr>
            <td class="left" >年龄:</td>
            <td class="right">
                <input type="text" id="age" name="age" class="layui-input" required />
            </td>
        </tr>
        <tr>
            <td class="left"  >性别:</td>
            <td class="right">
                <input type="radio" name="sex" value="男" title="男" checked>
                <input type="radio" name="sex" value="女" title="女">
            </td>
        </tr>
        <tr>
            <td class="left"  >电话:</td>
            <td class="right">
                <input type="text" id="tel" name="tel" class="layui-input" required />
            </td>
        </tr>
        <tr>
            <td class="left"  >描述:</td>
            <td class="right">
                <input type="text" id="description" name="description" class="layui-input" required />
            </td>
        </tr>
        <tr>
            <td class="left"  >创建时间:</td>
            <td class="right">
                <input type="text" id="createtm" name="createtm" VALUE="${user.createtm}" class="layui-input" required />
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div align="center">  <button type="submit" class="layui-btn" >提交</button>
                    &nbsp;&nbsp;
                    <button type="reset" class="layui-btn" >重置</button>
                    &nbsp;&nbsp;
                    <button type="button"  class="layui-btn" onClick="javaScript:deleteTab(${param.tabId});" >关闭</button>
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</body>
</html>
