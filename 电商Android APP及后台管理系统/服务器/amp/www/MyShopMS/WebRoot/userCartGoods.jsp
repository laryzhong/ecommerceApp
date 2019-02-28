<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
	request.setCharacterEncoding("utf-8");
	String username = (String)session.getAttribute("loginuser");
	if(username == null)
		response.sendRedirect("login.jsp");
%>
<jsp:include page="elements/main_head.jsp"/>
<body>
<jsp:include page="elements/main_menu.jsp"/>
<script type="text/javascript" >	
	function unite(id, num){
		var td_id = document.getElementById("id_" + id);
		var td_user = document.getElementById("user_" + id);
		var td_crdt = document.getElementById("crdt_" + id);
		var td_total = document.getElementById("total_" + id);
		td_id.rowSpan = num;
		td_user.rowSpan = num;
		td_crdt.rowSpan = num;
		td_total.rowSpan = num;
	}
</script>
<div id="content" class="wrap">
	<div class="list orderList">
			<table>
				<tr class="title">
					<th>用户名</th>
					<th>商品编号</th>
					<th>商品名称</th>
					<th>商品图片</th>
					<th>商品单价</th>
					<th class="price">商品数量</th>
					<th class="status">总价</th>
					
				</tr>
				<c:set var="td_id" value="0" />	
				<c:forEach var="userCartGoods" items="${userCartGoods}">
				<tr>
						<c:set var="td_id" value="${td_id+1}" />
						<td id="user_${td_id}">${userCartGoods.userName}</td>	
						<c:if test="${oid_count > 1}">
							<script type="text/javascript" >	
								unite(${td_id-1}, ${oid_count});
							</script>
						</c:if>					
						<td id="id_${td_id}">${userCartGoods.goodsId}</td>
						<td class="thumb"><img src="${userCartGoods.imageUrl}" /></td>					
						<td>${userCartGoods.text}</td>
						<td>${userCartGoods.price}</td>
						<td>${userCartGoods.goodsCount}</td>						
						<td id="total_${td_id}">${userCartGoods.price * userCartGoods.goodsCount}</td>
						
						<c:set var="oid_count" value="${oid_count+1}" />
						<c:set var="oid_count" value="0" />
					<c:set var="oid_temp" value="${userCartGoods.oid}"></c:set>
				</tr>
				</c:forEach>
				<c:if test="${td_id > 0}">
					<script type="text/javascript" >	
						unite(${td_id}, ${oid_count});
					</script>
				</c:if>
			</table>
	</div>
</div>
</body>
<jsp:include page="elements/main_bottom.html"/>
