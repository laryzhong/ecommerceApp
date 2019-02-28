package cn.jbit.myshopms.biz;

import java.util.List;

import cn.jbit.myshopms.dao.UserCartDao;


public interface UserCartBiz {
	public List<Object> findAllFromUserCart(int page_userCartgoods, int page_NO );
	public UserCartDao getUserCartDao();
	public void setOrderdao(UserCartDao userCartDao);
	public int count();

}
