package cn.jbit.myshopms.biz;

import java.util.List;

import cn.jbit.myshopms.dao.OrderDao;
import cn.jbit.myshopms.entity.Order;

@SuppressWarnings("rawtypes")
public interface OrderBiz {

	public boolean newOrder(Order order);

	public List findOrderByUser(String username, int page_goods, int page_NO );
	public OrderDao getOrderdao();
	public void setOrderdao(OrderDao orderdao);
	public String getCurrentOid(String username);
	public int count();
}
