package cn.jbit.myshopms.dao;

import java.util.List;

import cn.jbit.myshopms.entity.Goods;

public interface GoodsDao {

	public List<Goods> query(String sql );
	public int insert(Goods goods);
	public int count();
	public int setGoodsId();
	public int update(String sql);
}
