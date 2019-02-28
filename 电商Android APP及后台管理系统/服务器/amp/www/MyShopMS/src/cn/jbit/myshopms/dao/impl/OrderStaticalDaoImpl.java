package cn.jbit.myshopms.dao.impl;

import java.util.List;

import cn.jbit.myshopms.dao.BaseDao;
import cn.jbit.myshopms.dao.OrderStaticalDao;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class OrderStaticalDaoImpl extends BaseDao implements OrderStaticalDao {

	
	public List queryOrder(String sql) {
		//String[] columns = {"text","count", "sortName"};
		return query(sql, new String[]{"text","count","sortName"});
	}

}
