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


/*
 * 查找商品
 */
@SuppressWarnings({"serial","rawtypes"})
public class SearchServlet extends HttpServlet {

	private GoodsBiz goodsbiz = null;
	private GoodsDao goodsdao = null;

	@Override
	public void init() throws ServletException {		
		goodsdao = new GoodsDaoImpl();
		goodsbiz = new GoodsBizImpl();
		goodsbiz.setGoodsdao(goodsdao);
	}
	/*
	 * 查找商品功能
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String keywords = req.getParameter("keywords");
//		String keywords = new String(keywords_temp.getBytes("ISO-8859-1"), "utf-8");
		List goods =  goodsbiz.findGoodsLikeName(keywords);
		
		req.setAttribute("goods", goods);
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
}
