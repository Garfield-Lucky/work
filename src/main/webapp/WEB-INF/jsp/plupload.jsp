<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>上传文件(技术比中人不在手)</title>
    <!-- 首先需要引入plupload的源代码 -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/plupload/plupload.full.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index/xadmin.css?time=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css?time=<%=System.currentTimeMillis()%>">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js?time=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/xadmin.js?time=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common_layui.js?time=<%=System.currentTimeMillis()%>" type="text/javascript"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.css">
    	<style>
	body{ 
	width:600px;
	height:400px;
	font-size: 12px;
	background-color: #E8FFE8;
	}
	body,p,div{ padding: 0; margin: 0;}
	.wraper{ padding: 30px 0;}
	.progress{height: 10px;}
	.btn-wraper{ text-align: center;}
	.btn-wraper input{ margin: 0 10px;}
	#file-list{ width: 500px; margin: 20px auto;}
	#file-list li{ margin-bottom: 10px;list-style: none;}
	.file-name{ line-height: 30px; font-size:18px; font-family:Georgia;font-family:Arial; color:color:#777;}
	.tip1{text-align: center; font-size:14px; padding-top:10px;}
    .tip2{text-align: center; font-size:12px; padding-top:10px; color:#b00}
    .catalogue{ position: fixed; _position:absolute; _width:200px; left: 0; top: 0; border: 1px solid #ccc;padding: 10px; background: #eee}
    .catalogue a{ line-height: 30px; color: #0c0}
    .catalogue li{ padding: 0; margin: 0; list-style: none;}
    .file_delete{margin-left: 10px;}
     .upload_success{margin-left: 5%;color: green;font-size:18px}
     .file_delete{cursor:pointer;font-size:18px;color: orange;} 
     .action{text-align:center;width:100%;height:50px;margin:0px;margin-top: 20px; } 
    .closePage{text-align:center;width:100%;height:50px;margin:0px;position: absolute;bottom: 30px;} 
    </style>
</head>
<body>

<div>
    <%--<form name="form" id="form" method="post" class="layui-form" action="${pageContext.request.contextPath }/user/addUser" onsubmit="return ajaxSave(this);">--%>
        <table class="layui-table" width="100%">

            <tr>
                <td class="left" >文件:</td>
                <td class="right">
                    <input type="file"  name="file" class="layui-input js-file-form" required />
                </td>
            </tr>
            <tr>
                <td class="left" >上传:</td>
                <td class="right">
                    <input type="button" class="on-upload" onclick="uploadfile()" value="上传"/>
                </td>
            </tr>

        </table>

    <%--</form>--%>
</div>
    <!-- 这里我们只使用最基本的html结构：一个选择文件的按钮，一个开始上传文件的按钮(甚至该按钮也可以不要) -->
   <div class="action">
        <button id="browse" class="btn btn-info">选择文件</button>
        <button id="start_upload" class="btn btn-info">开始上传</button>
    </div>
    <div class="list">
	    <ul id="file-list">
	
	    </ul>
    </div>
    <div class="closePage">
     <button id="close" class="btn btn-success">关闭页面</button>
    </div>
    <script>

        function uploadfile() {
            var upload = function (file, chunk,timestamp,fileNum,all) {
                var formData = new FormData();//初始化一个FormData对象
                var blockSize = 1048576;//每块的大小 1024*1024 = 1M  这里改后台也要跟着改
                var nextSize = Math.min((chunk + 1) * blockSize, file.size);//读取到结束位置
                var fileData = file.slice(chunk * blockSize, nextSize);//截取部分文件块
                formData.append("file", fileData);//部分分片文件
                formData.append("chunk", chunk);//当前是第几个分片
                formData.append("fileName", file.name);//保存文件名字
                formData.append("timestamp", timestamp);//保存时间戳
                formData.append("fileNum",fileNum+1 );//当前上传的是第几个文件
                formData.append("all",all );//总文件数
                if (file.size <= nextSize) {//如果上传完成，则跳出继续上传
                    formData.append("finish", 200);
                }else{
                    formData.append("finish", 0);
                }
                $.ajax({
                    url: "${pageContext.request.contextPath }/file/uploadBySlices",
                    type: "POST",
                    data: formData,
                    processData: false,  // 告诉jQuery不要去处理发送的数据
                    contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
                    success: function (responseText) {
                        if (file.size <= nextSize) {//如果上传完成，则跳出继续上传
                            alert("上传成功");
                            return;
                        }
                        upload(file, ++chunk,timestamp,fileNum,all);//递归调用
                    }
                });
            };

            //获取时间戳
            var timestamp = (new Date()).getTime();
            var num = $(".js-file-form").length;

            for (var i=0;i<num;i++)
            {
                var file = $(".js-file-form")[i].files[0];
                upload(file, 0,timestamp,i,num);
            }

        }
   
    //实例化一个plupload上传对象
    var uploader = new plupload.Uploader({
        browse_button : 'browse', //触发文件选择对话框的按钮，为那个元素id
        url : '${pageContext.request.contextPath }/file/uploadFiles', //服务器端的上传页面地址
        flash_swf_url : '${pageContext.request.contextPath}/js/plupload/Moxie.swf', //swf文件，当需要使用swf方式进行上传时需要配置该参数
        silverlight_xap_url : '${pageContext.request.contextPath}/js/plupload/Moxie.xap', //silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
        file_data_name : 'file',
        filters : [ {
            title : 'Image files',
            extensions : 'jpg,gif,png'
        }, {
            title : 'Office files',
            extensions : 'doc,docx,excel,ppt,txt,mpp,xls,xlsx,pdf'
        } ]
    });    

    //在实例对象上调用init()方法进行初始化
    uploader.init();
    
    uploader.bind('Init',function(uploader){
    	var DG = frameElement.lhgDG;	
        DG.curWin.toCleanFileId();
    });

    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader.bind('FilesAdded',function(uploader,files){
        //每个事件监听函数都会传入一些很有用的参数，
        //我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
    	for(var i = 0, len = files.length; i<len; i++){
			var file_name = files[i].name; //文件名
			//构造html来更新UI
			var html = '<li id="file-' + files[i].id +'">'+
			'<p class="fileNum"><span class="file-name">' + file_name + '</span>'+
			'<span class="file_delete" onclick="fileDelete(this)" data-val='+files[i].id+'>删除</span>'+
			'<span class="upload_success"><span><p class="progress progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></p></p></li>';
			$(html).appendTo('#file-list');
		}
    });
    uploader.bind('UploadProgress',function(uploader,file){
        //每个事件监听函数都会传入一些很有用的参数，
        //我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
        $('#file-'+file.id+' .progress').css('width',file.percent + '%');//控制进度条
    });
    
    //完成一个文件上传时触发
    uploader.bind('FileUploaded',function(uploader,file,responseObject){
    	console.log(responseObject);
    	//var DG = frameElement.lhgDG;
    	if(JSON.parse(responseObject.response).code=='0'){
 	       $('#file-'+file.id+' .file_delete').remove();
 	       $('.upload_success').html('成功');
     	}else{
     		 $('.upload_success').html('失败');
     	}
     //   DG.curWin.toAddFileReback(JSON.parse(responseObject.response).fileId);
   
    });
    

    //最后给"开始上传"按钮注册事件
    document.getElementById('start_upload').onclick = function(){
    	if (uploader.files.length > 0) {// 判断队列中是否有文件需要上传
            uploader.start();   
        } else {
            alert('请选择至少一个文件进行上传！');
        }
    }
  
    //删除选择的文件
   function fileDelete(this_){
	    $(this_).parent().parent().remove();
    	var toremove = '';
    	var id=$(this_).attr("data-val");
    	for(var i in uploader.files){
    	if(uploader.files[i].id === id){
    	toremove = i;
    	 }
    	}
    	uploader.files.splice(toremove, 1);  
    	};

    	//上传完毕，关闭页面
    	$("#close").click(function(){
         deleteTab(${param.tabId});
  		});

    </script>
</body>
</html>