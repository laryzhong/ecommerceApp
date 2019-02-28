package cn.jbit.myshopms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.jbit.myshopms.dao.BaseDao;
import cn.jbit.myshopms.dao.NewStockUploadImagesDao;


@SuppressWarnings("rawtypes")
public class NewStockUploadImagesDaoImpl extends BaseDao implements NewStockUploadImagesDao {

	public int insert( List list) {
		
		String table = "e_goods_banners";
		return insert(table, list);
	}
	public int insert_e_goods_details( List list) {
		
		String table = "e_goods_details";
		return insert(table, list);
	}
	public int count() {
		String sql = "select count(*) from e_goods_banners";
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
	public int count_details() {
		String sql = "select count(*) from e_goods_details";
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
	public int getGoodsId() {
		String sql = "select max(goodsId) from e_goods";
		openconnection();
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
				i = rs.getInt(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}
		return i;
	}
	@Override
	public List<Map> query(String sql) {
		// TODO Auto-generated method stub
		return query(sql, new String[]{"goodsId"});
	}
	

}
