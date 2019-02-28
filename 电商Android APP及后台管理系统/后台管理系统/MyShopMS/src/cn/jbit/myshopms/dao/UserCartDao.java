package cn.jbit.myshopms.dao;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface UserCartDao {

	public List query(String sql );
	public int count();
}
