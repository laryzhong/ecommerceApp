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
	$(document).ready(function(){	  		  	
	  	$("#newStock").click(function(){
	  	var formData = new FormData($("#form")[0]); 

		$.ajax({
			type: "POST",
			url: "uploadGoodsImgs.jsp",
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
			<li class="past">1.填写上架商品信息</li>
			<li class="last">2.商品上架成功</li>
		</ul>
	</div>
	<form method="post" id="form" action="NewStockUploadImages">
		<dl>
			<dt>商品轮播图:</dt>
			<dd class="button" >			
	      		<input type="file" id="productimageUrl" name="imageUrl" style="width:166px" />
	      		<input type="file" id="productimageUrl" name="imageUrl" style="width:166px" /><br />
	      		<input type="file" id="productimageUrl" name="imageUrl" style="width:166px" />
	    		<span id="productimageUrlnull"></span>
			</dd>
			<dt>商品详情图:</dt>
			<dd class="button" >			
	      		<input type="file" id="productimageUrl" name="imageUrl" style="width:166px" />
	    		<input type="file" id="productimageUrl" name="imageUrl" style="width:166px" /><br />
	      		<input type="file" id="productimageUrl" name="imageUrl" style="width:166px" />
	    		<span id="productimageUrlnull"></span>
			</dd>
			<dt>商品规格参数图:</dt>
			<dd class="button" >			
	      		<input type="file" id="productimageUrl" name="imageUrl" style="width:166px" />
	    		<input type="file" id="productimageUrl" name="imageUrl" style="width:166px" /><br />
	      		<input type="file" id="productimageUrl" name="imageUrl" style="width:166px" />
	    		<span id="productimageUrlnull"></span>
			</dd>
			
			<dt></dt>
			<dd class="button"><input id="newStock" class="input-newstock" type="submit" onsubmit="up()" name="newstock" value="" /></dd>
		</dl>
	</form>
	
</div>	
<jsp:include page="elements/index_bottom.html"></jsp:include>
