package cn.jbit.myshopms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import cn.jbit.myshopms.biz.UserCartBiz;
import cn.jbit.myshopms.biz.impl.UserCartBizImpl;
import cn.jbit.myshopms.dao.UserCartDao;
import cn.jbit.myshopms.dao.impl.UserCartDaoImpl;
import cn.jbit.myshopms.util.PageTools;
/*
 * 显示全部图书
 */
public class ShowCartServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserCartBiz userCartBiz = null;
	private UserCartDao userCartDao = null;

	@Override
	public void init() throws ServletException {		
		userCartDao = new UserCartDaoImpl();
		userCartBiz = new UserCartBizImpl();
		userCartBiz.setOrderdao(userCartDao);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String NO_str = req.getParameter("current_userCartGoods_NO");
		int NO = NO_str==null?1:Integer.valueOf(NO_str);
		int total_goods = userCartBiz.count(); 
		int goods_num = PageTools.goods_num;
		int total_page = total_goods/goods_num + 1;
		//获得用户购物车全部商品信息
		List<Object> userCartGoods =  userCartBiz.findAllFromUserCart(PageTools.goods_num, NO);
		//System.out.println(userCartGoods);
		req.setAttribute("userCartGoods", userCartGoods);
		req.setAttribute("current_userCartGoods_NO", NO);
		req.setAttribute("total_userCartGoods_page", total_page);		
		req.getRequestDispatcher("userCartGoods.jsp").forward(req, resp);
	}
}
