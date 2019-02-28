package cn.jbit.myshopms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.jbit.myshopms.dao.BaseDao;
import cn.jbit.myshopms.dao.GoodsDao;
import cn.jbit.myshopms.entity.Goods;


@SuppressWarnings({"unchecked","rawtypes"})
public class GoodsDaoImpl extends BaseDao implements GoodsDao {
	public int insert(Goods goods) {
		String table = "e_goods";
		List<Comparable> list = new ArrayList<Comparable>();
		list.add(goods.getGid());	
		list.add(goods.getGoodsname());
		list.add(goods.getImage());
		list.add(goods.getSpanSize());
		list.add(goods.getPrice());
		list.add(goods.getContent());
		list.add(goods.getSortId());
		list.add(goods.getStock());
		return insert(table, list);
	}

	public List query(String sql) {

		String[] columns = { "goodsId", "text", "imageUrl", "stock", "price","desc","sortId" };
		return query(sql, columns);
	}

	public int count() {
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
	public int setGoodsId() {
		String sql = "select max(goodsId) from e_goods";
		openconnection();
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
				i = rs.getInt(1);
			
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
