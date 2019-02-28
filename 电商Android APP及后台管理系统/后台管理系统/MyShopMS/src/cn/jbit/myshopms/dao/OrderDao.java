package cn.jbit.myshopms.dao;

import java.util.List;

import cn.jbit.myshopms.entity.Order;


@SuppressWarnings("rawtypes")
public interface OrderDao {

	public List query(String sql );
	public List queryOrder(String sql );
	public int insert(Order order);
	public int count();
	public List queryByUserId(String sql);
}
