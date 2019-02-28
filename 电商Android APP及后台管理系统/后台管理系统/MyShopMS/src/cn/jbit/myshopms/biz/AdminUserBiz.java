package cn.jbit.myshopms.biz;

import cn.jbit.myshopms.dao.AdminUserDao;
import cn.jbit.myshopms.entity.AdminUserInfo;

public interface AdminUserBiz {

	public boolean checkLogin(String username, String password);
	public boolean userExists(String username);
	public boolean addAminUser(AdminUserInfo userinfo);
	public void setAdminUserdao(AdminUserDao userdao);
	public AdminUserDao getAdminUserdao();
}
