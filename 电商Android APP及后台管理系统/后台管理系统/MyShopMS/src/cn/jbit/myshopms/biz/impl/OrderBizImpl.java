package cn.jbit.myshopms.biz.impl;

import java.util.List;
import java.util.Map;

import cn.jbit.myshopms.biz.OrderBiz;
import cn.jbit.myshopms.dao.OrderDao;
import cn.jbit.myshopms.entity.Order;

@SuppressWarnings("rawtypes")
public class OrderBizImpl implements OrderBiz {

	private OrderDao orderdao = null;
	public OrderDao getOrderdao() {
		return orderdao;
	}

	public void setOrderdao(OrderDao orderdao) {
		this.orderdao = orderdao;
	}

	public List findOrderByUser(String username, int page_goods, int page_NO) {
		String sql = "select * " +
				"from e_order_list, e_user , e_goods " +
				"where e_order_list.goodsId = e_goods.goodsId and "
				+ "e_order_list.userId = e_user.userId order by e_order_list.orderTime";
		return orderdao.query(sql);
	}


	public boolean newOrder(Order order) {		
		int row = orderdao.insert(order);
		return row>0?true:false;
	}

	public int count() {
		
		return orderdao.count();
	}

	public String getCurrentOid(String userId) {
		String sql = "select * from e_order_list where userId = '" + userId + "' order by orderTime desc";
		List orders = orderdao.queryByUserId(sql);
		Map order = (Map)orders.get(0);
		return new String("" + order.get("OID"));
	}

}
