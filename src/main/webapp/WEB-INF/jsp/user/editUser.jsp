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
    layui.use('form', function(){
        var form = layui.form;
        form.on('switch(status)', function(data){
            console.log(data.elem); //得到checkbox原始DOM对象
            console.log(data.elem.checked); //开关是否开启，true或者false
            console.log(data.value); //开关value值，也可以通过data.elem.value得到
            console.log(data.othis); //得到美化后的DOM对象
           if(data.elem.checked==true){
               $("#status").val(0);
           }else{
               $("#status").val(1);
           }
        });
    });

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
<form name="form" id="form" method="post" class="layui-form" action="${pageContext.request.contextPath }/user/editUser" onsubmit="return ajaxSave(this);">
    <table class="layui-table" width="100%">
        <tr>
            <th colspan="2" align="left">修改人员信息</th>
        </tr>
        <tr>
            <td class="left" >账号:</td>
            <td class="right">
                <input type="text" id="userName" name="userName" VALUE="${user.userName}" class="layui-input" required disabled/>
                <input type="hidden" id="id" name="id" VALUE="${user.id}" class="layui-input" required />
            </td>
        </tr>
        <tr>
            <td class="left" >密码:</td>
            <td class="right">
                <input type="text" id="password" name="password" VALUE="${user.password}" class="layui-input" required />
            </td>
        </tr>
        <tr>
            <td class="left" >真实姓名:</td>
            <td class="right">
                <input type="text" id="trueName" name="trueName" VALUE="${user.trueName}" class="layui-input" required />
            </td>
        </tr>
        <tr>
            <td class="left"  >年龄:</td>
            <td class="right">
                <input type="text" id="age" name="age" VALUE="${user.age}" class="layui-input" required />
            </td>
        </tr>
        <tr>
            <td class="left"  >性别:</td>
            <td class="right">
                <input type="radio" name="sex" value="男" title="男" <c:if test="${user.sex =='男'}"> checked </c:if>>
                <input type="radio" name="sex" value="女" title="女" <c:if test="${user.sex =='女'}"> checked </c:if>>
            </td>
        </tr>
        <tr>
            <td class="left"  >电话:</td>
            <td class="right">
                <input type="text" id="tel" name="tel" VALUE="${user.tel}" class="layui-input" required />
            </td>
        </tr>
        <tr>
            <td class="left"  >微信:</td>
            <td class="right">
                <input type="text" id="wx" name="wx" VALUE="${user.wx}" class="layui-input" required />
            </td>
        </tr>
        <tr>
            <td class="left"  >状态:</td>
            <td class="right">
                <input type="checkbox" id="status" name="status" lay-skin="switch" lay-text="开启|禁用" lay-filter="status" VALUE="${user.status}" <c:if test="${user.status ==0}"> checked </c:if>/>
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
