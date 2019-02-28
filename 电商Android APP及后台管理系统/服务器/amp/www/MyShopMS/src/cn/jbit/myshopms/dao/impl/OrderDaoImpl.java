package cn.jbit.myshopms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.jbit.myshopms.dao.BaseDao;
import cn.jbit.myshopms.dao.OrderDao;
import cn.jbit.myshopms.entity.Order;


@SuppressWarnings({"rawtypes", "unchecked"})
public class OrderDaoImpl extends BaseDao implements OrderDao {

	public int insert(Order order) {
		String table = "e_order_list";
		List list = new ArrayList();		
		list.add(order.getOid());
		list.add(order.getUsername());
		return insert(table, list);
	}

	public List query(String sql) {
		String[] columns = {"OID","username", "goodsId", "orderTime", 
				"price", "imageUrl", "text","count"};
		return query(sql, columns);
	}
	
	public List queryOrder(String sql) {
		String[] columns = {"text","count", "sortId"};
		return query(sql, columns);
	}
	
	public int count() {
		String sql = "select count(*) from e_order_list";
		openconnection();
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		
		return i;
	}

	public List queryByUserId(String sql) {
		String[] columns = {"OID", "userId"};
		return query(sql, columns);
	}
}
