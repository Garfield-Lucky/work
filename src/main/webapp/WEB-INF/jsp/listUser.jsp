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
    <title>用户列表</title>
    <base href="<%=basePath%>" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
</head>
<body>
<!--通过bootstrap的栅格系统布局-->
<div class="container">

    <!--标题-->
    <div class="row">
        <div class="col-md-12">
            <h1>用户增删改查</h1>
        </div>

    </div>

    <!--按钮-->
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">新增</button>
        </div>
    </div>

    <!--显示表格数据-->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>id</th>
                    <th>userName</th>
                    <th>trueName</th>
                    <th>age</th>
                    <th>sex</th>
                    <th>tel</th>
                    <th>wx</th>
                    <th>操作</th>
                </tr>


                <c:forEach items="${pageInfo.list}" var="user">
                    <tr>
                        <th>${user.id}</th>
                        <th>${user.userName}</th>
                        <th>${user.trueName}</th>
                        <th>${user.age}</th>
                        <th>${user.sex}</th>
                        <th>${user.tel}</th>
                        <th>${user.wx}</th>
                        <th>
                            <button class="btn btn-primary">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                编辑
                            </button>

                            <button class="btn btn-danger">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                删除
                            </button>

                        </th>
                    </tr>
                </c:forEach>

            </table>
        </div>

    </div>

    <!--显示分页信息-->
    <div class="row">
        <!--文字信息-->
        <div class="col-md-6">
            当前第 ${pageInfo.pageNum} 页.总共 ${pageInfo.pages} 页.一共 ${pageInfo.total} 条记录
        </div>

        <!--点击分页-->
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">

                    <li><a href="${pageContext.request.contextPath}/user/userList?pageNum=1">首页</a></li>

                    <!--上一页-->
                    <li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <a href="${pageContext.request.contextPath}/user/userList?pageNum=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">«</span>
                            </a>
                        </c:if>
                    </li>

                    <!--循环遍历连续显示的页面，若是当前页就高亮显示，并且没有链接-->
                    <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                        <c:if test="${page_num == pageInfo.pageNum}">
                            <li class="active"><a href="#">${page_num}</a></li>
                        </c:if>
                        <c:if test="${page_num != pageInfo.pageNum}">
                            <li><a href="${pageContext.request.contextPath}/user/userList?pageNum=${page_num}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>

                    <!--下一页-->
                    <li>
                        <c:if test="${pageInfo.hasNextPage}">
                            <a href="${pageContext.request.contextPath}/user/userList?pageNum=${pageInfo.pageNum+1}"
                               aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </c:if>
                    </li>

                    <li><a href="${pageContext.request.contextPath}/user/userList?pageNum=${pageInfo.pages}">尾页</a></li>
                </ul>
            </nav>
        </div>

    </div>


</div>


</body>
</html>
