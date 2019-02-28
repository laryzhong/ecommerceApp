package cn.jbit.myshopms.dao;

import java.util.List;
import java.util.Map;



public interface OrderStaticalDao {

	@SuppressWarnings("rawtypes")
	public List<Map> queryOrder(String sql );
}
