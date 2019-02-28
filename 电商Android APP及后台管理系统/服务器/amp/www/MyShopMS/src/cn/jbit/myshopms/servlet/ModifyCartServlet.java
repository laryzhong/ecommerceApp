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
 * �޸Ļ��Ƴ����ﳵ�е�ͼ��
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
	 * �Ƴ�����
	 */
	private void remove(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		req.setCharacterEncoding("utf-8");
		String gid_str = req.getParameter("gid");
		String gid = gid_str;
		// ��ȡ���ﳵ
		List goods_in_cart = (List) req.getSession().getAttribute(
				"goods_in_cart");
		// ���ҹ��ﳵ�б��޸Ĺ���ͼ�鲢�Ƴ���ͼ��
		for (int i = 0; i < goods_in_cart.size(); i++) {
			Goods book = (Goods) goods_in_cart.get(i);
			if (gid.equals(book.getGid())) {
				// �Ƴ�ͼ��
				goods_in_cart.remove(i);
				// �޸Ŀ��
				goodsbiz.changeStock(gid, "1");
				break;
			}
		}
		req.getSession().setAttribute("goods_in_cart_count",
				goods_in_cart.size());
	}

	/*
	 * �޸Ĳ���
	 */
	private void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("utf-8");
		// ��ȡͼ��id���޸ĺ������
		String gid_str = req.getParameter("gid");
		String count_str = req.getParameter("count");
		String gid = gid_str;
		int count = Integer.valueOf(count_str);
		// ��ȡ���ﳵ
		List goods_in_cart = (List) req.getSession().getAttribute(
				"goods_in_cart");
		// ���ҹ��ﳵ�б��޸Ĺ���ͼ�鲢�޸���Ӧ��Ϣ
		for (int i = 0; i < goods_in_cart.size(); i++) {
			Goods goods = (Goods) goods_in_cart.get(i);
			if (Integer.parseInt(gid) == goods.getGid()) {
				// ��ȡ�޸�ǰ��ͼ������
				int old_count = goods.getCount();
				// ���õ�ǰͼ���������
				goods.setCount(count);
				// �޸Ŀ��
				goodsbiz.changeStock(gid, new String("" + (old_count - count)));
				break;
			}
		}
	}
}
