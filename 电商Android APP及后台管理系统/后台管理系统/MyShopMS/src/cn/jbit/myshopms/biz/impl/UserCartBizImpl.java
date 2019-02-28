package cn.jbit.myshopms.biz.impl;

import java.util.List;

import cn.jbit.myshopms.biz.UserCartBiz;
import cn.jbit.myshopms.dao.UserCartDao;


@SuppressWarnings("unchecked")
public class UserCartBizImpl implements UserCartBiz {

	UserCartDao userCartDao = null;


	/*
	 * 返回图书数量
	 */
	@Override
	public int count() {

		return userCartDao.count();
	}

	/**
	 * 获取用户购物车全部商品
	 * 
	 * @param page_goods
	 *            在分页的数据个数
	 * @param page_NO
	 *            页码
	 */
	@Override
	public List<Object> findAllFromUserCart(int page_userCartgoods, int page_NO) {
		
				int now_page = (page_NO - 1) * page_userCartgoods;
				String mysql = "select * from e_shop_cart,e_goods,e_user where "
						+ "e_shop_cart.goodsId=e_goods.goodsId "
						+ "and e_shop_cart.userId=e_user.userId "
						+ " limit " + now_page + " , "
						+ page_userCartgoods;
				return (List<Object>) userCartDao.query(mysql);
	}

	@Override
	public UserCartDao getUserCartDao() {
		// TODO Auto-generated method stub
		return userCartDao;
	}

	@Override
	public void setOrderdao(UserCartDao userCartDao) {
		// TODO Auto-generated method stub
		this.userCartDao = userCartDao;
	}

}
