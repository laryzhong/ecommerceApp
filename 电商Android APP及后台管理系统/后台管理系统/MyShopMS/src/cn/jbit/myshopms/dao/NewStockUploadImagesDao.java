package cn.jbit.myshopms.dao;

import java.util.List;
import java.util.Map;


@SuppressWarnings("rawtypes") 
public interface NewStockUploadImagesDao {
	public int insert(List list);
	public int insert_e_goods_details(List list);
	public int count();
	public int count_details();
	public int getGoodsId();
	public List<Map> query(String sql);
}
