<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="elements/index_head.jsp"/>
<script type="text/javascript">
	//全部输入项非空检查
	//检查用户名是否存在
	function isUsernameLegal(){
		var adminName = document.getElementById("adminName").value;
		var usernull = document.getElementById("usernull");
		//使用Ajax检查用户是否存在		
		if(adminName == null || adminName == ""){
			usernull.innerHTML = "<font color=\"red\">用户名不能为空！</font>";
			return false;
		}
		isUserExsit();
		return true;
	}
	//检查密码内容位数
	function isPasswordLegal(){
		var adminPassword = document.getElementById("adminPassword").value;
		var pwdnull = document.getElementById("nullpassword");
		
		if(adminPassword == null || adminPassword == ""){
			pwdnull.innerHTML = "<font color=\"red\">密码不能为空！</font>";
			return false;
		}else if(adminPassword.length < 8){
			pwdnull.innerHTML = "<font color=\"red\">密码长度小于8位！！</font>";
			return false;
		}else
			pwdnull.innerHTML = "";
		return true;
	}
	//检查密码是否一致
	function isRepasswordLegal(){
		var readminPassword = document.getElementById("readminPassword").value;
		var adminPassword = document.getElementById("adminPassword").value;
		var pwdnull = document.getElementById("nullrePassword");
		
		if(readminPassword == null || readminPassword == ""){
			pwdnull.innerHTML = "<font color=\"red\">确认密码不能为空！</font>";
			return false;
		}else if(readminPassword != adminPassword){
			pwdnull.innerHTML = "<font color=\"red\">两次密码输入不一致！</font>";
			return false;
		}else
			pwdnull.innerHTML = "";
		return true;
	}
	function check(){
		var adminName = document.getElementById("adminName");
		if(!isUsernameLegal()){
			adminName.focus();
			return false;
		}else if(!isPasswordLegal()){
			adminName.focus();
			return false;
		}
		else if(!isRepasswordLegal()){
			adminName.focus();
			return false;
		}		
		return true;		
	}
	 
	//Ajax查看用户是否已经被注册
	function isUserExsit(){
		var xmlHttp;
		//根据不同浏览器初始化xmlHttp
		try{
			//IE 6+
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");				
		}catch(e){
			try{
				//FireFox
				xmlHttp = new XMLHttpRequest();		
			}catch(e){
				try{
					//IE 5.5+
					xmlHttp =  new ActiveXObject("Microsoft.XMLHTTP");
				}catch(e){
					alert("您的浏览器不支持Ajax！");
					return false;
				}
			}
		}
		
		var adminName = document.getElementById("adminName").value;
		xmlHttp.open("POST", "regist?action=check&&adminName= "+adminName, true);		
		xmlHttp.send(null);
		xmlHttp.onreadystatechange = function()
		{
			if( xmlHttp.readyState == 4 )
			{
				var usernull = document.getElementById("usernull");
				var temp = xmlHttp.responseText;
				if(temp == "true"){
					usernull.innerHTML = "<font color=\"red\">当前用户名已被注册！</font>";
					return false;	
				}					
				else if(temp == "false"){
					usernull.innerHTML = "<font color=\"green\">可以注册，赶快注册吧！</font>";
					return true;					
				}
			}
		};
	}
</script>

<body>
<div id="header" class="wrap">
	<div id="logo" onclick="javascript:location.href='goods' ">商城后台管理系统</div>
	<div id="navbar">
	</div>
</div>
<div id="register">
	<div class="title">
		<h2>注册商城管理员</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="current">1.填写管理员信息</li>
			<li class="unpass">2.添加成功</li>
		</ul>
	</div>
	<form method="post" action="regist" onsubmit="return check()"  >
		<dl>
			<dt>用 户 名：</dt>
			<dd><input class="input-text" type="text" id="adminName" name="adminName" onblur="isUsernameLegal()"/><span id="usernull"></span><span id="alreadyExsits"></span></dd>
			<dt>密　　码：</dt>
			<dd><input class="input-text" type="password" id="adminPassword" name="adminPassword" onblur="isPasswordLegal()" /><span id="nullpassword"><span style="color:green">密码至少8位</span></span><span id="simplepassword"></span></dd>
			<dt>确认密码：</dt>
			<dd><input class="input-text" type="password" id="readminPassword" name="readminPassword" onblur="isRepasswordLegal()" /><span id="nullrePassword"></span><span id="uneq"></span></dd>
			<dt></dt>
			<dd class="button"><input class="input-reg" type="submit" name="register" value="" /></dd>
		</dl>
	</form>
</div>	
<jsp:include page="elements/index_bottom.html"></jsp:include>
