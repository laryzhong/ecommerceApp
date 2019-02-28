package cn.jbit.myshopms.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.jbit.myshopms.dao.impl.DelGoodsDaoImpl;

/*
 * ���������붩����
 */
@SuppressWarnings("serial")
public class DelGoodsServlet extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); 
		//��ȡ�û���ѡ���¼ܵ���Ʒ
		String[] item = req.getParameterValues("goodsId");
//		String delStr = "";
		DelGoodsDaoImpl delGoodsDaoImpl = new DelGoodsDaoImpl();
		for(int i=0;i<item.length;i++){
			//ִ���¼ܲ���
			delGoodsDaoImpl.delete(Integer.parseInt(item[i]));
		}
			
//		System.out.println(delStr);
		resp.sendRedirect("goods");
	}


}
