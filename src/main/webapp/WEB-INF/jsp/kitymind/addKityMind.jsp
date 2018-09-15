<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>CDA在线脑图</title>

	<link href="favicon.ico" type="image/x-icon" rel="shortcut icon">

	<!-- bower:css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/bootstrap/dist/css/bootstrap.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/codemirror/lib/codemirror.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/hotbox/hotbox.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/kityminder-core/dist/kityminder.core.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/color-picker/dist/color-picker.min.css" />
	<!-- endbower -->


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/naotu/kityMind/dist/kityminder.editor.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-3.3.6/css/bootstrap.min.css">

	<style>
		html, body {
			margin: 0;
			padding: 0;
			height: 100%;
			overflow: hidden;
		}
		h1.editor-title {
			background: #393F4F;
			color: white;
			margin: 0;
			height: 40px;
			font-size: 14px;
			line-height: 40px;
			font-family: 'Hiragino Sans GB', 'Arial', 'Microsoft Yahei';
			font-weight: normal;
			padding: 0 20px;
		}
		div.minder-editor-container {
			position: absolute;
			top: 40px;
			bottom: 0;
			left: 0;
			right: 0;
		}
		#svgdata{
		height: 100%;
		width: 100%;
		}

		#form_data{
		padding-top: 20px;
		padding-left: 20%;
		}


	</style>
</head>
<body ng-app="kityminderDemo" ng-controller="MainController">

<h1 class="editor-title">CDA在线脑图</h1>
<kityminder-editor on-init="initEditor(editor, minder)"></kityminder-editor>
<div id="svgdata">
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					请输入脑图名称
				</h4>
			</div>
			<div class="modal-body">
				<form id="form_data">
					<table width="85%" border="0">
					  <tr>
					  <td>脑图名称：</td>
					   <td><input id="kityMindName" class="kityMindName form-control" placeholder="请输入名称" type="text" /></td>
					  </tr>
					   <tr>
					     <td>
						   <div class="line" style="height: 20px;"></div>
					    </td>
					  </tr>
					   <tr>
					  <td>是否公开：</td>
					   <td><input class="isOpen" type="radio" name="isOpen" value="0" checked/>公开
					   <input class="isOpen" type="radio" name="isOpen" value="1" style="margin-left: 20px;" />私有</td>
					  </tr>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary confirm">
					提交
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</body>

<!-- bower:js -->
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/jquery/dist/jquery.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/bootstrap/dist/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/angular/angular.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/codemirror/lib/codemirror.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/codemirror/mode/xml/xml.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/codemirror/mode/javascript/javascript.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/codemirror/mode/css/css.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/codemirror/mode/htmlmixed/htmlmixed.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/codemirror/mode/markdown/markdown.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/codemirror/addon/mode/overlay.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/codemirror/mode/gfm/gfm.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/angular-ui-codemirror/ui-codemirror.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/marked/lib/marked.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/kity/dist/kity.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/hotbox/hotbox.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/json-diff/json-diff.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/kityminder-core/dist/kityminder.core.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/color-picker/dist/color-picker.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/dist/saveSvgAsPng.js"></script>

<script src="${pageContext.request.contextPath}/script/bsgrid/plugins/bootstrap/2.3.2/js/bootstrap.min.js"></script>

<%-- <script src="${pageContext.request.contextPath}/script/jquery/jquery-1.6.2.min.js" type="text/javascript"></script> --%>


<!-- endbower -->

<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/dist/kityminder.editor.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/dist/diy.js"> --%>

<script src="${pageContext.request.contextPath}/script/cda/bank/liClick.js"	type="text/javascript"></script>

 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-3.3.6/css/bootstrap.min.css">


<script>
(function(){
	var oldData;
	var html = '';
	html += '<a class="diy export" data-type="md">导出文本</a>',
	html += '<a class="diy export" data-type="json">导出json</a>',
	html += '<a class="diy export" data-type="svg">导出png图片</a>',
	html += '<button class="diy">',
	html += '<a class="diy save" data-toggle="modal" data-target="#myModal">保存</a>',
	html += '</button>';

	$('.editor-title').append(html);

	$('.diy').css({
		// 'height': '30px',
		// 'line-height': '30px',
		'margin-top': '0px',
		'float': 'right',
		'background-color': '#fff',
		'min-width': '60px',
		'text-decoration': 'none',
		 color: '#999',
		'padding': '0 10px',
		border: 'none',
		'border-right': '1px solid #ccc',
		'cursor': 'pointer',

	});

	$('.confirm').on('click',function(){

		var createUserName = '${user.userName}';
		var mindName = $(".kityMindName").val();
		var isOpen =$('input[name="isOpen"]:checked').val();

		if(mindName.trim()!='')
		{
		//拿到json数据，所以需要调用editor.minder.exportData
		editor.minder.exportData('json').then(function(content){
			 $.ajax({
		 			type : "POST",
		 			url : "${pageContext.request.contextPath}/kityMind/save",
		 			async:false,
		 			data : {'createUserName':createUserName,'content':content,'deleteFlag':0,'isOpen':isOpen,'mindName':mindName},
		 			dataType : 'json',
		 			success : function(data) {
		 				if(data!=null&&data.Type=='Y')
	 					{
                            alert('保存成功!')
	 					}else{
	 						alert('保存失败!')
	 					}
		 			},
		 			error : function() {
		 				alert("error");
		 			}
		 		});
		  });
		}else{
			alert('脑图名称不能为空');
		}

	});

	$(document).on('click','.save',function(event){

		//获取焦点
		$("#kityMindNameInput").find(".kityMindName").focus();

	});

	$(document).on('mouseover', '.export', function(event) {
		// 链接在hover的时候生成对应数据到链接中
		event.preventDefault();
		var $this = $(this),
				type = $this.data('type'),
				exportType;
		switch(type){
			case 'km':
				exportType = 'json';
				break;
			case 'md':
				exportType = 'markdown';
				break;
			case 'svg':
				exportType = 'svg';
				break;
			default:
				exportType = type;
				break;
		}

		editor.minder.exportData(exportType).then(function(content){
			switch(exportType){
				case 'json':
					console.log($.parseJSON(content));
					break;
				default:
					console.log(content);
					break;
			}
			//导出文件
			if(type!='svg')
			{
				var blob = new Blob([content]),
				url = URL.createObjectURL(blob);
				var aLink = $this[0];
				aLink.href = url;
			    aLink.download = $('#node_text1').text()+'.'+type;
			    $('#svgdata').empty();
			}else{
				$('#svgdata').html(content);
			}


		});

	}).on('click', '.export', function(event) {
		// 禁止点击状态下取消跳转
// 		var $this = $(this);
		if($('#svgdata').html()!='')
		{
			var type='png';
			var canvas = $('#svgdata').find('svg')[0];
			saveSvgAsPng(canvas,$('#node_text1').text()+'.'+type);
		}

	});

	// 导入
	window.onload = function() {
		var fileInput = document.getElementById('fileInput');

		fileInput.addEventListener('change', function(e) {
			var file = fileInput.files[0],
					// textType = /(md|km)/,
					fileType = file.name.substr(file.name.lastIndexOf('.')+1);
			console.log(file);
			switch(fileType){
				case 'md':
					fileType = 'markdown';
					break;
				case 'km':
				case 'json':
					fileType = 'json';
					break;
				default:
					console.log("File not supported!");
					alert('只支持.km、.md、.json文件');
					return;
			}
			var reader = new FileReader();
			reader.onload = function(e) {
				var content = reader.result;
                 alert(content);
				editor.minder.importData(fileType, content).then(function(data){
					console.log(data)

					$(fileInput).val('');
				});
			}
			reader.readAsText(file);
		});
	}

})();
</script>


<script>
	angular.module('kityminderDemo', ['kityminderEditor'])
			.config(function (configProvider) {
				configProvider.set('imageUpload', '${pageContext.request.contextPath}/plugin/naotu/kityMind/server/imageUpload.php');
			})
			.controller('MainController', function($scope) {
				$scope.initEditor = function(editor, minder) {
					window.editor = editor;
					window.minder = minder;
				};
			});


</script>
<script src="${pageContext.request.contextPath}/plugin/naotu/kityMind/dist/saveSvgAsPng.js"></script>

</html>