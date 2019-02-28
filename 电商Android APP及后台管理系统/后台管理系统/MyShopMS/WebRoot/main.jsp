<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="cn.jbit.myshopms.util.PageTools"%>
<%@ page import="cn.jbit.myshopms.entity.Goods"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:include page="elements/main_head.jsp"/>
<%	
	request.setCharacterEncoding("utf-8"); 
	String username = (String)session.getAttribute("loginuser");
	if(username == null)
		response.sendRedirect("login.jsp");
%>
<%
	int NO = 0;
	int total_page = 0;
	@SuppressWarnings("rawtypes")
	List goods = (List)request.getAttribute("goods");
	if(request.getAttribute("current_goods_NO") != null){
		NO = (Integer)request.getAttribute("current_goods_NO");
		total_page = (Integer)request.getAttribute("total_goods_page");
	}
%>
<body>
<jsp:include page="elements/main_menu.jsp"/> 
<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="DelGoods" action="DelGoods">
			<table>
				<tr class="title">
					<th class="checker"></th>
					<th>商品名称</th>
					<th class="content">商品简介</th>
					<th class="price">价格</th>
					<th class="store">库存</th>
					<th class="view">图片预览</th>
				</tr>
				<c:forEach var="goods" items="${goods}">
					<tr>
						<td><input type="checkbox" name="goodsId" value="${goods.goodsId}" /></td>
						<td class="title" style="text-align:center;">${goods.text}</td>
						<td style="display:none"><input type="hidden" name="title" value = "${goods.gid}:${goods.text}" /></td>
						<td class="title" style="text-align:center;">${goods.desc}</td>
						<td style="display:none"><input type="hidden" name="title" value = "${goods.gid}:${goods.desc}"/></td>
						<td style="text-align:center;">￥${goods.price}</td>
						<td style="display:none"><input type="hidden" name="price" value = "${goods.gid}:${goods.price}"/></td>
						<td style="text-align:center;">${goods.stock}</td>
						<td style="display:none"><input type="hidden" name="stock" value = "${goods.gid}:${goods.spanSize}"/></td>
						<td class="thumb" style="text-align:center;"><img style="max-width: 100%;max-height: 100%;" src="${goods.imageUrl}" /></td>
						<td style="display:none;width:0px;"><input type="hidden" name="image" value = "${goods.gid}:${goods.imageUrl}"/>
					</tr>
				</c:forEach>
			</table>
			<%if(request.getAttribute("current_goods_NO") != null){ %>
			<div class="page-spliter">
				<a href="goods">首页</a>
					<%for(int i = 1; i <= total_page; i++){ %>
						<%if(i == NO){ %>
							<span class="current"><%=i %></span>
						<%continue;}%>
						<a href="goods?current_goods_NO=<%=i %>"><%=i %></a>
					<%} %>
				<a href="goods?current_goods_NO=<%=total_page %>">尾页</a>
			</div>
			<%} %>
			<div class="button"><input class="input-btn" type="submit" name="submit" value="" /></div>
		</form>
	</div>
</div>
</body>
<jsp:include page="elements/main_bottom.html"/>