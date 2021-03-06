<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CDA在线脑图</title>
  <link href="favicon.ico" type="image/x-icon" rel="shortcut icon">
  <link rel="stylesheet" href="src/kityminder.css" rel="stylesheet">

  <style type="text/css">
      body {
          margin: 0;
          padding: 0;
          height: 100%;
      }

      #minder-view {
          position: absolute;
          border: 1px solid #ccc;
          left: 10px;
          top: 10px;
          bottom: 10px;
          right: 10px;
      }
  </style>

  <script type="text/javascript" src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/kity/dist/kity.min.js"></script>
</head>
<body>
   <script id="minder-view" type="application/kityminder" minder-data-type="json">
   ${kityMind.content}
    </script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/naotu/kityMind/bower_components/kityminder-core/dist/kityminder.core.min.js"></script>
<script type="text/javascript">
    // 创建 km 实例
    /* global kityminder */
    var km = window.km = new kityminder.Minder();
    km.setup('#minder-view');
    km.disable();
    km.execCommand('hand');
</script>
</html>
