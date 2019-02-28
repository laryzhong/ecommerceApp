package cn.jbit.myshopms.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.jbit.myshopms.dao.NewStockUploadImagesDao;
import cn.jbit.myshopms.dao.impl.NewStockUploadImagesDaoImpl;


/*
 * 新品相关图上传
 */
@SuppressWarnings({"serial","rawtypes"})
@MultipartConfig
public class NewStockUploadImagesServlet extends HttpServlet {

	private NewStockUploadImagesDao newStockUploadImagesDao = null;

	@Override
	public void init() throws ServletException {
		newStockUploadImagesDao = new NewStockUploadImagesDaoImpl();
	}

	/*
	 * 入库
	 */
	@SuppressWarnings("unchecked")
	private void upload(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, Exception {
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		String[] productimageUrl = req.getParameterValues("imageUrl");
		String sql = "select goodsId from e_goods ";
		List<Map> orderStatical = newStockUploadImagesDao.query(sql);
		int goodsId = 0; 
		//获取刚上架商品的ID
		for(int i = 0;i<orderStatical.size();i++){
			int id = Integer.parseInt(orderStatical.get(i).get("goodsId").toString());
			if(goodsId < id){
				goodsId = id;
			}
		}
		String addr = "http://192.168.191.1/image/goodsImages/";
		for(int i=0;i<productimageUrl.length/3;i++){
			String imageUrl = addr + productimageUrl[i];
			int goodsBannersId = newStockUploadImagesDao.count()+1;
			List list = new ArrayList();
			list.add(goodsBannersId);
			list.add(goodsId);
			list.add(imageUrl);
			newStockUploadImagesDao.insert(list);
		}
		for(int i=productimageUrl.length/3;i<productimageUrl.length;i++){
			String imageUrl = addr + productimageUrl[i];
			int goodsDetailsId = newStockUploadImagesDao.count_details()+1;
			List list = new ArrayList();
			list.add(goodsDetailsId);
			list.add(goodsId);
			if(i < 6){
				list.add(1);
			}else{
				list.add(2);
			}
			list.add(imageUrl);
			newStockUploadImagesDao.insert_e_goods_details(list);
		}
		
		resp.sendRedirect("newstockGoodsImagesUpload_success.jsp");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			upload(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
