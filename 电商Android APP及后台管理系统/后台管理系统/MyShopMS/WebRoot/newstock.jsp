<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%	
	request.setCharacterEncoding("utf-8"); 
	String username = (String)session.getAttribute("loginuser");
	if(username == null)
		response.sendRedirect("login.jsp");
%>

<jsp:include page="elements/index_head.jsp"/>
<script src="js/jquery-2.1.3.js"  ></script>
<script type="text/javascript" charset="utf-8">
	//全部输入项非空检查
	//检查商品是否存在
	function isProductNameLegal(){
		var productname = document.getElementById("productname").value;
		var productnamenull = document.getElementById("productnamenull");
		//alert(productname);
		//使用Ajax检查商品是否存在		
		if(productname == null || productname == ""){
			productnamenull.innerHTML = "<font color=\"red\">商品名不能为空！</font>";
			return false;
		}else{
			productnamenull.innerHTML = "";
		}
		isProductExsit();
		return true;
	}
	//检查商品简介是否填写
	function isProductInfoLegal(){
		var productinfo = document.getElementById("productinfo").value;
		var productinfonull = document.getElementById("productinfonull");
		
		if(productinfo == null || productinfo == ""){
			productinfonull.innerHTML = "<font color=\"red\">商品简介不能为空！</font>";
			return false;
		}else
			productinfonull.innerHTML = "";
		return true;
	}
	//检查商品类别是否选择
	function isSortLegal(){
		var sort = document.getElementById("sort").value;
		var sortnull = document.getElementById("sortnull");
		
		if(sort.value == -1 || sort.value == "-1"){
			sortnull.innerHTML = "<font color=\"red\">请选择商品分类！</font>";
			return false;
		}else
			sortnull.innerHTML = "";
		return true;
	}
	//检查商品价格是否设置
	function isProductPriceLegal(){
		var productprice = document.getElementById("productprice").value;
		var productpricenull = document.getElementById("productpricenull");
		if(productprice<=0){
			productpricenull.innerHTML = "<font color=\"red\">请设置合理的价格！</font>";
			return false;
		}else
			productpricenull.innerHTML = "";
		return true;
	}
	//检查商品库存是否设置
	function isProductStockLegal(){
		var productstock = document.getElementById("productstock").value;
		var productstocknull = document.getElementById("productstocknull");
		if(productstock<=0){
			productstocknull.innerHTML = "<font color=\"red\">请设置合理的库存！</font>";
			return false;
		}else
			productstocknull.innerHTML = "";
		return true;
	}
	
	
	function check(){
		var productname = document.getElementById("productname");
		if(!isProductNameLegal()){
			productname.focus();
			return false;
		}else if(!isProductInfoLegal()){
			productname.focus();
			return false;
		}else if(!isSortLegal()){
			productname.focus();
			return false;
		}else if(!isProductPriceLegal()){
			productname.focus();
			return false;
		}else if(!isProductStockLegal()){
			productname.focus();
			return false;
		}
		return true;		
	}
	//Ajax查看商品是否已经上架
	function isProductExsit(){
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
		
		var productname = document.getElementById("productname").value;
				
		xmlHttp.open("POST", "regist?action=check&&text="+productname, true);	
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"); 
		//alert(xmlHttp.responseText);	
		xmlHttp.send(null);
		xmlHttp.onreadystatechange = function()
		{
			if( xmlHttp.readyState == 4 )
			{
				var productnamenull = document.getElementById("productnamenull");
				var temp = xmlHttp.responseText;
				if(temp == "true"){
					usernull.innerHTML = "<font color=\"red\">当前商品已上架！</font>";
					return false;	
				}					
				else if(temp == "false"){
					productnamenull.innerHTML = "<font color=\"green\">该商品未上架，快上架吧！</font>";
					return true;					
				}
			}
		};
	}
	$(document).ready(function(){	  	
	  	 /* $("#newStock").mouseover(function(){
	     $("form").attr("action","newStock");
	  	});
	  	$("#newStock").mouseout(function(){
	     $("form").attr("action","");
	  	}); */
	  	
	  	$("#newStock").click(function(){
	  	var formData = new FormData($("#form")[0]); 

		$.ajax({
			type: "POST",
			url: "uploadProductImg.jsp",
			processData : false,
			async : false,
			data: formData,
			contentType : false,
			success: function(obj){
				$("#productimageUrlnull").html("上传成功");
		}
});
	  	
	  	});
	  		
});

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<body>
<div id="header" class="wrap">
	<div id="logo" onclick="javascript:location.href='goods' ">商城后台管理系统</div>
	<div id="navbar">
	</div>
</div>
<div id="register">
	<div class="title">
		<h2>新商品上架</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="current">1.填写商品信息</li>
			<li class="unpass">2.上架成功</li>
		</ul>
	</div>
	<form method="post" id="form" action="newStock" onsubmit="retrun check()">
		<dl>
			<dt>商品名：</dt>
			<dd><input class="input-text" type="text" id="productname" name="productname" onblur="isProductNameLegal()"/><span id="productnamenull"></span><span id="isProductExsit"></span></dd>
			<dt>商品简介：</dt>
			<dd><input class="input-text" type="text" id="productinfo" name="productinfo" onblur="isProductInfoLegal()"/><span id="productinfonull"></span></dd>
			<dt>商品类别：</dt>
			<dd><select class="input-text" id="sortId" name="sortId" style="height:30px;margin:10px;" onblur="isSortLegal()">
				  <option value ="-1">——请选择——</option>
				  <option value ="0">热门推荐</option>
				  <option value ="1">手机数码</option>
				  <option value ="2">家用电器</option>
				  <option value="3">电脑办公</option>
				  <option value="4">男装</option>
				  <option value ="5">男鞋</option>
				  <option value ="6">女装</option>
				  <option value="7">女鞋</option>
				</select><span id="sortnull"></span></dd>	
			<dt>商品图片:</dt>
			<dd class="button" >			
	      		<input type="file" id="productimageUrl" name="productimageUrl" style="width:166px" />
	    		<span id="productimageUrlnull"></span>
			</dd>
			<dt>商品单价：</dt>
			<dd><input class="input-text" type="number" id="productprice" name="productprice" placeholder="单位：元" onblur="isProductPriceLegal()"/><span id="productpricenull"></span></dd>
			<dt>商品库存：</dt>
			<dd><input class="input-text" type="number" id="productstock" name="productstock" placeholder="单位：件" onblur="isProductStockLegal()"/><span id="productstocknull"></span></dd>
			
			<dt></dt>
			<dd class="button"><input id="newStock" class="input-newstock" type="submit" onsubmit="up()" name="newstock" value="" /></dd>
		</dl>
	</form>
	
</div>	
<jsp:include page="elements/index_bottom.html"></jsp:include>
