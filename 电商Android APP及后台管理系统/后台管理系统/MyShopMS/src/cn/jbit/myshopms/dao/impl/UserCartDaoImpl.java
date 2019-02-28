package cn.jbit.myshopms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import cn.jbit.myshopms.dao.BaseDao;
import cn.jbit.myshopms.dao.UserCartDao;



@SuppressWarnings("rawtypes")
public class UserCartDaoImpl extends BaseDao implements UserCartDao {

	public List query(String sql) {

		String[] columns = { "userName","goodsId", "text", "imageUrl", "price","goodsCount" };
		return query(sql, columns);
	}

	public int count() {
		String sql = "select count(*) from e_shop_cart";
		openconnection();
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}

		return i;
	}

	public int update(String sql) {

		return deleteOrUpdate(sql);
	}

}
