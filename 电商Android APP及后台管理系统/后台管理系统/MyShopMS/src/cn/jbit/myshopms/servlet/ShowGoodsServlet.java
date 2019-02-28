package cn.jbit.myshopms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import cn.jbit.myshopms.biz.GoodsBiz;
import cn.jbit.myshopms.biz.impl.GoodsBizImpl;
import cn.jbit.myshopms.dao.GoodsDao;
import cn.jbit.myshopms.dao.impl.GoodsDaoImpl;
import cn.jbit.myshopms.util.PageTools;
/*
 * 显示全部图书
 */
@SuppressWarnings({"serial","rawtypes"})
public class ShowGoodsServlet extends HttpServlet {

	private GoodsBiz goodsbiz = null;
	private GoodsDao goodsdao = null;

	@Override
	public void init() throws ServletException {		
		goodsdao = new GoodsDaoImpl();
		goodsbiz = new GoodsBizImpl();
		goodsbiz.setGoodsdao(goodsdao);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String NO_str = req.getParameter("current_goods_NO");
		int NO = NO_str==null?1:Integer.valueOf(NO_str);
		int total_goods = goodsbiz.count(); 
		int goods_num = PageTools.goods_num;
		int total_page = 
				total_goods%goods_num == 0 ? 
						total_goods/goods_num : total_goods/goods_num + 1;
		//获得全部图书信息
		List goods =  goodsbiz.findAllGoods(PageTools.goods_num, NO);
		
		req.setAttribute("goods", goods);
		req.setAttribute("current_goods_NO", NO);
		req.setAttribute("total_goods_page", total_page);		
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
}
