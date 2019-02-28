<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>

<body>

<div id="header" class="wrap">
	<div id="logo" onclick="javascript:location.href='goods' ">商城后台管理系统</div>
	<div id="navbar">
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="register">
	<div class="title">
		<h2>新品上架</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="past">1.填写上架商品信息</li>
			<li class="last">2.商品上架成功</li>
		</ul>
	</div>
	<div class="success">
		<div class="information">
			<p>恭喜：商品上架成功！</p>
			<p><a href="login.jsp">点此进入管理中心&gt;&gt;</a></p>
			<p><a href="newstock.jsp">点此继续上架商品&gt;&gt;</a></p>
		</div>
	</div>
</div>
<div id="footer" class="wrap">
		2015级软件工程S班软件工程第一项目组 &copy; 版权所有
</div>
</body>
</html>
