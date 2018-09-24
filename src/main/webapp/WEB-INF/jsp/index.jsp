<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<title>EasyWork</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index/xadmin.css?time=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css?time=<%=System.currentTimeMillis()%>">
<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/swiper.jquery.min.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/layui/layui.js?time=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/xadmin.js?time=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common_layui.js?time=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
<style type="text/css">

</style>
<head>
<body  style="padding: 0px;">
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <font size="5px"> EasyWork </font>
    </div>
    <div class="open-nav">
        <i class="iconfont">&#xe699;</i>
    </div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item" style="float:right;margin-right:50px;color: #000;"><a href="javascript:;">${sessionUser.userName }</a>
            <dl class="layui-nav-child">
                <dd>
                    <a href="${pageContext.request.contextPath}/loginOut" style="color: black;">退出</a>
                </dd>
            </dl>
        </li>
    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<div class="wrapper" style="table-layout: fixed;">
    <!-- 左侧菜单开始 -->
    <div class="left-nav">
        <div id="side-nav">
            <ul id="nav">
                <li class="list"><a href="javascript:void(0);"> <i class="layui-icon">&#xe857;</i> 主要功能 <i class="layui-icon nav_right">&#xe61a;</i></a>
                    <ul class="sub-menu opened">
                        <li><a href="javascript:;" class="site-demo-active" data-type="tabAdd" data-id="100" data-name="在线脑图">
                            <i class="iconfont"></i> 在线脑图</a>
                        </li>
                        <li><a href="javascript:;" class="site-demo-active" data-type="tabAdd" data-id="101" data-name="用户信息管理">
                            <i class="iconfont"></i> 用户信息管理</a>
                        </li>
                        <li><a href="javascript:;" class="site-demo-active"  data-type="tabAdd" data-id="102" data-name="文件上传">
                            <i class="iconfont"></i> 文件上传</a>
                        </li>
                        <li><a href="javascript:;" class="site-demo-active"  data-type="tabAdd" data-id="103" data-name="全文检索">
                            <i class="iconfont"></i> 全文检索</a>
                        </li>

                    </ul>
                </li>

            </ul>
        </div>
    </div>
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content" style="width: 85%;">
        <div class="content layui-tab layui-tab-card" lay-filter="mytab" lay-allowclose="true" style="width: 100%;height: 100%;">
            <ul class="layui-tab-title" style="border-color: #FFB800;">
                <li class="layui-this" lay-id="0" style="border-style: none;"><span>首页</span></li>
            </ul>
            <div class="layui-tab-content" style="height: 100%;padding: 0px;">
                <!--    		      所有新打开的tab都放在下面这个div里面 -->
                <div class="layui-tab-item layui-show">

                </div>
            </div>
        </div>

    </div>
    <!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->

<script>
    layui.use(['element','layer'], function(){
        var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
        //各个模块url
        var url = {
            100:'${pageContext.request.contextPath}/kityMind/list?time=<%=System.currentTimeMillis()%>',
            101:'${pageContext.request.contextPath}/user/list?time=<%=System.currentTimeMillis()%>',
            102:'${pageContext.request.contextPath}/file/?time=<%=System.currentTimeMillis()%>',
            103:'${pageContext.request.contextPath}/elasticSearch/list?time=<%=System.currentTimeMillis()%>',

        };

        //触发事件
        var active = {
            //打开一个新的tab
            tabAdd: function(data){
                // if(data.context.dataset.id==100) {
                //     addTab('test', url[data.context.dataset.id], data.context.dataset.id);
                // }else {
                    $(".page-content").load(url[data.context.dataset.id]);
                // }
            }
        };

        $('.site-demo-active').on('click', function(){
            var othis = $(this);
            var type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });


    });
</script>
</body>
</html>