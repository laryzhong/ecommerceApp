package cn.jbit.myshopms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cn.jbit.myshopms.dao.AdminUserDao;
import cn.jbit.myshopms.dao.BaseDao;
import cn.jbit.myshopms.entity.AdminUserInfo;


public class AdminUserDaoImpl extends BaseDao implements AdminUserDao {

	public int insert(AdminUserInfo adminUserInfo) {
		
		String table = "a_user";
		List<String> list = new ArrayList<String>();
		list.add(adminUserInfo.getAdminId());
		list.add(adminUserInfo.getAdminName());
		list.add(adminUserInfo.getAdminPassword());
		list.add(adminUserInfo.getAdminLastLoginTime());
		
		return insert(table, list);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List query(String sql) {
		String[] columns = {"AdminName", "AdminPassword", "AdminLastLoginTime" };
		return query(sql, columns);
	}

}
