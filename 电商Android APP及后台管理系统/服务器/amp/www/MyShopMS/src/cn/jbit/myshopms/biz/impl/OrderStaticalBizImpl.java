package cn.jbit.myshopms.biz.impl;

import java.util.List;
import cn.jbit.myshopms.biz.OrderStaticalBiz;
import cn.jbit.myshopms.dao.BaseDao;
import cn.jbit.myshopms.dao.OrderStaticalDao;

public class OrderStaticalBizImpl extends BaseDao implements OrderStaticalBiz{

	private OrderStaticalDao orderStaticalDao = null;
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List OrderStatical() {
		String sql = "select * " +
				" from e_order_list , e_goods , e_sort_lists " +
				" where e_order_list.goodsId = e_goods.goodsId and "
				+ " e_goods.sortId = e_sort_lists.sortId ";
		return orderStaticalDao.queryOrder(sql);
	}





}
