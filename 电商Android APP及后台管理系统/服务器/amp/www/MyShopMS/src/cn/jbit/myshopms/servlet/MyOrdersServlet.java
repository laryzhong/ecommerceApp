package cn.jbit.myshopms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jbit.myshopms.biz.OrderBiz;
import cn.jbit.myshopms.biz.impl.OrderBizImpl;
import cn.jbit.myshopms.dao.OrderDao;
import cn.jbit.myshopms.dao.impl.OrderDaoImpl;
/*
 * 增加订单，清空购物车信息
 */
@SuppressWarnings({"serial","rawtypes"})
public class MyOrdersServlet extends HttpServlet {

	private OrderBiz orderbiz = null;
	private OrderDao orderdao = null;

	@Override
	public void init() throws ServletException {
		
		orderdao = new OrderDaoImpl();
		orderbiz = new OrderBizImpl();
		orderbiz.setOrderdao(orderdao);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String username = (String)req.getSession().getAttribute("loginuser");
		List orders = orderbiz.findOrderByUser(username, 0, 0);
		req.setAttribute("orders", orders);
		req.getRequestDispatcher("orderlist.jsp").forward(req, resp);
	}
}
