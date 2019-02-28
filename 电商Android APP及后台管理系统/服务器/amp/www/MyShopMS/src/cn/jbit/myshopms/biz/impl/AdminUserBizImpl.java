package cn.jbit.myshopms.biz.impl;

import java.util.List;

import cn.jbit.myshopms.biz.AdminUserBiz;
import cn.jbit.myshopms.dao.AdminUserDao;
import cn.jbit.myshopms.entity.AdminUserInfo;

public class AdminUserBizImpl implements AdminUserBiz {

	private AdminUserDao userdao = null;
	@Override
	public AdminUserDao getAdminUserdao() {
		return userdao;
	}

	@Override
	public void setAdminUserdao(AdminUserDao userdao) {
		this.userdao = userdao;
	}

	@Override
	public boolean addAminUser(AdminUserInfo adminUserInfo) {
		
		int row = userdao.insert(adminUserInfo);
		return row>0?true:false;
	}
	/*
	 * 执行登陆请求操作
	 */
	@Override
	public boolean checkLogin(String username, String password) {
		String sql = "select * from a_user " +
				"where AdminName = '" + username +
				"' and AdminPassword = '" + password + "'";
		List<AdminUserInfo> list = userdao.query(sql);
		return list.size()>0?true:false;
	}
	/*
	 * 查看用户是否存在
	 */
	@Override
	public boolean userExists(String username) {
		String sql = "select * from a_user " +
				"where AdminName = '" + username + "'";
		List<AdminUserInfo> list = userdao.query(sql);
		return list.size()>0?true:false;		
	}



}
