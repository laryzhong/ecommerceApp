package cn.jbit.myshopms.dao.impl;

import java.sql.SQLException;


import cn.jbit.myshopms.dao.BaseDao;
import cn.jbit.myshopms.dao.DelGoodsDao;

public class DelGoodsDaoImpl extends BaseDao implements DelGoodsDao {

	public int delete(int goodsId) {
		//��Ʒ��
		String table = "e_goods";
		int count = 0;
		//ɾ����Ʒ����ǰ̨�����goodsIdֵ������Ʒ
		String sql = "delete from " + table + " where goodsId = " + goodsId;
		count += deleteOrUpdate(sql);

		return count;
	}

	public int count() {
		//����������Ʒ����
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
