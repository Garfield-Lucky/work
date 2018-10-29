<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>上传文件(技术比中人不在手)</title>
    <!-- 首先需要引入plupload的源代码 -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/plupload/plupload.full.min.js"></script>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.css">
    	<style>
	body{ 
	width:800px;
	height:500px;
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
  		  //var DG = frameElement.lhgDG;
  	      // DG.cancel()
  		});

    </script>
</body>
</html>