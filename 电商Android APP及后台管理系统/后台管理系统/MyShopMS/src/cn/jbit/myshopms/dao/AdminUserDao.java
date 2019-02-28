package cn.jbit.myshopms.dao;

import java.util.List;

import cn.jbit.myshopms.entity.AdminUserInfo;

public interface AdminUserDao {

	public List<AdminUserInfo> query(String sql );
	public int insert(AdminUserInfo adminUserInfo);
}
