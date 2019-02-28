package cn.jbit.myshopms.biz.impl;


import java.util.List;

import cn.jbit.myshopms.biz.NewStockUploadImagesBiz;
import cn.jbit.myshopms.dao.NewStockUploadImagesDao;
@SuppressWarnings("rawtypes") 
public class NewStockUploadImagesBizImpl implements NewStockUploadImagesBiz {

	private NewStockUploadImagesDao newStockUploadImagesDao = null;
	

	public boolean add(List list) {
		
		int row = newStockUploadImagesDao.insert(list);
		return row>0?true:false;
	}


	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getGoodsId() {
		// TODO Auto-generated method stub
		return 0;
	}





}
