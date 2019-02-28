package cn.jbit.myshopms.dao.impl;

import java.sql.SQLException;


import cn.jbit.myshopms.dao.BaseDao;
import cn.jbit.myshopms.dao.DelGoodsDao;

public class DelGoodsDaoImpl extends BaseDao implements DelGoodsDao {

	public int delete(int goodsId) {
		//商品表
		String table = "e_goods";
		int count = 0;
		//删除商品表中前台传入的goodsId值所在商品
		String sql = "delete from " + table + " where goodsId = " + goodsId;
		count += deleteOrUpdate(sql);

		return count;
	}

	public int count() {
		//返回所有商品数量
		String sql = "select count(*) from e_goods";
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

}
