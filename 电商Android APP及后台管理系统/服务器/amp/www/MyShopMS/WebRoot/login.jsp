<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="elements/index_head.jsp"></jsp:include>
<%
	request.setCharacterEncoding("utf-8");
	String username = (String)session.getAttribute("loginuser");
	if(username != null)
		response.sendRedirect("goods");
%>

<script type="text/javascript">
	//非空检查
	function isUsernameNull(){
		var username = document.getElementById("adminName").value;
		var usernull = document.getElementById("usernull");
		
		if(username == null || username == ""){
			usernull.innerHTML = "<font color=\"red\">用户名不能为空！</font>";
			return false;
		}else
			usernull.innerHTML = "";
		return true;
	}
	function isPasswordNull(){
		var password = document.getElementById("adminPassword").value;
		var pwdnull = document.getElementById("pwdnull");
		
		if(password == null || password == ""){
			pwdnull.innerHTML = "<font color=\"red\">密码不能为空！</font>";
			return false;
		}else
			pwdnull.innerHTML = "";
		return true;
	}
	function check(){
		if(!isUsernameNull()){
			return false;
		}else if(!isPasswordNull()){
			return false;
		}
		return true;		
	}
</script>

<body>
<div id="header" class="wrap">
	<div id="logo">商城后台管理系统 </div>
	<div id="navbar">
	</div>
</div>
<div id="login">
	<h2>管理员登陆</h2>
	<form method="post" action="login" onsubmit="return check()">
		<dl>
			<dt>账户名：</dt>
			<dd><input class="input-text" type="text" id="adminName" name="adminName" onblur="isUsernameNull()"/><span id="usernull"></span></dd>
			<dt>密　码：</dt>
			<dd><input class="input-text" type="password" id="adminPassword" name="adminPassword" onblur="isPasswordNull()"/><span  id="pwdnull"></span></dd>
			<dt>&nbsp;</dt>
			<dd class="button"><input class="input-btn" type="submit" name="submit" value="" /></dd>
		</dl>
	</form>
</div>
<jsp:include page="elements/index_bottom.html"></jsp:include>

