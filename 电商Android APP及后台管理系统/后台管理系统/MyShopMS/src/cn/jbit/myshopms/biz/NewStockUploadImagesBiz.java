package cn.jbit.myshopms.biz;

import java.util.List;


@SuppressWarnings("rawtypes") 
public interface NewStockUploadImagesBiz {
	public boolean add(List list);
	public int count();
	public int getGoodsId();

}
