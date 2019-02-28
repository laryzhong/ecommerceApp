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
					<th class="orderId">订单编号</th>					
					<th class="userName">收货人</th>					
					<th class="createTime">下单时间</th>
					<th class="status">总价</th>
					<th>订单商品</th>
					<th>商品名称</th>
					<th>商品单价</th>
					<th class="price">商品数量</th>
				</tr>
				<c:set var="oid_count" value="0" />
				<c:set var="td_id" value="0" />	
				<c:forEach var="order" items="${orders}">
				<tr>
					<c:if test="${oid_temp != order.OID}">
						<c:set var="td_id" value="${td_id+1}" />
						<td id="id_${td_id}">${order.OID}</td>
						<td id="user_${td_id}">${order.username}</td>						
						<td id="crdt_${td_id}">${order.orderTime}</td>
						<td id="total_${td_id}">${order.price * order.count}</td>
						<c:if test="${oid_count > 1}">
							<script type="text/javascript" >	
								unite(${td_id-1}, ${oid_count});
							</script>
						</c:if>
						<c:set var="oid_count" value="0" />
					</c:if>
					<td class="thumb"><img src="${order.imageUrl}" /></td>					
					<td>${order.text}</td>
					<td>${order.price}</td>
					<td>${order.count}</td>
					<c:set var="oid_count" value="${oid_count+1}" />
					<c:set var="oid_temp" value="${order.oid}"></c:set>
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
