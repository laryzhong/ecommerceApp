package cn.jbit.myshopms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * 新品上架
 */
@SuppressWarnings("serial")
public class NewStockServlet extends HttpServlet {

	private GoodsBiz goodsbiz = null;
	private GoodsDao goodsdao = null;

	@Override
	public void init() throws ServletException {
		
		goodsdao = new GoodsDaoImpl();
		goodsbiz = new GoodsBizImpl();
		goodsbiz.setGoodsdao(goodsdao);
	}
	
	
	private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("utf-8");
		String productname = new String(req.getParameter("productname").getBytes("ISO8859-1"),"utf-8");
		//检查用户名是否已存在
		boolean isGoodsExists = goodsbiz.goodsExists(productname);
		resp.setCharacterEncoding("utf-8");
		if(isGoodsExists){			
			resp.getWriter().write("true");
			resp.getWriter().close();
		}else{
			resp.getWriter().write("false");
			resp.getWriter().close();
		}
	}
	/*
	 * 上架功能
	 */
	private void newstock(HttpServletRequest req, HttpServletResponse resp) throws IOException, Exception{
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		String productname = new String(req.getParameter("productname").getBytes("ISO8859-1"),"utf-8");
		String productinfo = new String(req.getParameter("productinfo").getBytes("ISO8859-1"),"utf-8");
		int sortId = Integer.parseInt(new String(req.getParameter("sortId").getBytes("ISO8859-1"),"utf-8"));
		String productimageUrl = "./goodsImages/" + new String(req.getParameter("productimageUrl").getBytes("ISO8859-1"),"utf-8");
		//System.out.println(productimageUrl);
		String productprice = new String(req.getParameter("productprice").getBytes("ISO8859-1"),"utf-8");
		String productstock = new String(req.getParameter("productstock").getBytes("ISO8859-1"),"utf-8");
		Goods goods = new Goods();
		//goods.setGid(goodsbiz.setGoodsId());
		
		goods.setGoodsname(productname);
		goods.setContent(productinfo);
		goods.setSortId(sortId);
		goods.setImage(productimageUrl);
		goods.setPrice(productprice);
		goods.setSpanSize(2);
		goods.setStock(Integer.parseInt(productstock));
		if(!goodsbiz.addGoods(goods)){
			
			resp.setCharacterEncoding("utf-8");
			PrintWriter pw = resp.getWriter();
			resp.setHeader("Content-type", "text/html;charset=UTF-8");  
			pw.println("<script type=\"text/javascript\" >");
			pw.println("alert(\"上架失败！请重新尝试！\");");
			pw.println("open(\"newstock.jsp\",\"_self\");");
			pw.println("</script>");
			return;
		}		
		resp.sendRedirect("newstock_success.jsp");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//查看当前执行的动作是什么(check：检查商品，null：上架)
		String action = req.getParameter("action");
		//System.out.println(action);
		if(action==null)
			try {
				newstock(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			check(req, resp);	
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
