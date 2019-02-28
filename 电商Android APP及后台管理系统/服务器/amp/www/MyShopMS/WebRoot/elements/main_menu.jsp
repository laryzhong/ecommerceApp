<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<script type="text/javascript" src="./js/jquery-2.1.3.js" ></script>
<script type="text/javascript" src="./js/jquery.js" ></script>
<div id="header" class="wrap">
	<div id="logo" onclick="javascript:location.href='goods' ">商城后台管理系统</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li class="current"><span style="BLACK">欢迎您，管理员<strong>${loginuser}</strong></span>&nbsp;&nbsp;&nbsp;</li>
				<li><a href="goods">首页</a></li>
				<li><a href="showOrder">所有订单</a></li>
				<li><a href="orderStatistical">订单统计</a></li>
				<li><a href="cart">用户购物车商品</a></li>
				<li><a href="newstock.jsp">新商品上架</a></li>
				<li><a href="register.jsp">添加管理员</a></li>
				<li><a href="logout.jsp">退出管理</a></li>
			</ul>
		</div>
		<form method="post" name="search" action="search">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>