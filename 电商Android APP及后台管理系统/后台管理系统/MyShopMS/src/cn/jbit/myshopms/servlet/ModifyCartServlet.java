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
import cn.jbit.myshopms.entity.Goods;


/*
 * 修改或移除购物车中的图书
 */
@SuppressWarnings({"serial","rawtypes"})
public class ModifyCartServlet extends HttpServlet {

	private GoodsBiz goodsbiz = null;
	private GoodsDao goodsdao = null;

	@Override
	public void init() throws ServletException {
		goodsdao = new GoodsDaoImpl();
		goodsbiz = new GoodsBizImpl();
		goodsbiz.setGoodsdao(goodsdao);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); 
		String action = req.getParameter("action");
		if (action.equals("remove"))
			remove(req, resp);
		else if (action.equals("modify"))
			modify(req, resp);
	}

	/*
	 * 移除操作
	 */
	private void remove(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		req.setCharacterEncoding("utf-8");
		String gid_str = req.getParameter("gid");
		String gid = gid_str;
		// 获取购物车
		List goods_in_cart = (List) req.getSession().getAttribute(
				"goods_in_cart");
		// 查找购物车中被修改过的图书并移除该图书
		for (int i = 0; i < goods_in_cart.size(); i++) {
			Goods book = (Goods) goods_in_cart.get(i);
			if (gid.equals(book.getGid())) {
				// 移除图书
				goods_in_cart.remove(i);
				// 修改库存
				goodsbiz.changeStock(gid, "1");
				break;
			}
		}
		req.getSession().setAttribute("goods_in_cart_count",
				goods_in_cart.size());
	}

	/*
	 * 修改操作
	 */
	private void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("utf-8");
		// 获取图书id和修改后的数量
		String gid_str = req.getParameter("gid");
		String count_str = req.getParameter("count");
		String gid = gid_str;
		int count = Integer.valueOf(count_str);
		// 获取购物车
		List goods_in_cart = (List) req.getSession().getAttribute(
				"goods_in_cart");
		// 查找购物车中被修改过的图书并修改相应信息
		for (int i = 0; i < goods_in_cart.size(); i++) {
			Goods goods = (Goods) goods_in_cart.get(i);
			if (Integer.parseInt(gid) == goods.getGid()) {
				// 获取修改前的图书数量
				int old_count = goods.getCount();
				// 设置当前图书的新数量
				goods.setCount(count);
				// 修改库存
				goodsbiz.changeStock(gid, new String("" + (old_count - count)));
				break;
			}
		}
	}
}
