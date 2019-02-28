<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
 <body>
 
    <%
     SmartUpload smart = new SmartUpload();//实例化上传组件
     smart.initialize(pageContext);  //初始化上传操作
     smart.upload();//上传操作
     smart.save("D:/amp/www/image/goodsImages");//将上传文件保存在upload文件中
     %>
<script src="js/jquery-2.1.3.js"  ></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function(){
	   		//history.go(-1);
	
	});
    </script>
  </body>
</html>
