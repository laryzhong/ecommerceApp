package cn.jbit.myshopms.biz.impl;

import java.util.List;

import cn.jbit.myshopms.biz.GoodsBiz;
import cn.jbit.myshopms.dao.GoodsDao;
import cn.jbit.myshopms.entity.Goods;



@SuppressWarnings({ "unchecked", "rawtypes" })
public class GoodsBizImpl implements GoodsBiz {

	GoodsDao goodsdao = null;
	@Override
	public GoodsDao getGoodsdao() {
		return goodsdao;
	}
	@Override
	public void setGoodsdao(GoodsDao goodsdao) {
		this.goodsdao = goodsdao;
	}
	@Override
	public boolean addGoods(Goods goods) {

		int row = goodsdao.insert(goods);
		return row > 0 ? true : false;
	}

	/**
	 * 获取全部商品
	 * 
	 * @param page_goods
	 *            在分页的数据个数
	 * @param page_NO
	 *            页码
	 */
	@Override
	public List findAllGoods(int page_goods, int page_NO) {
		/*
		   分页oracle sql
		 String sql = "select * from(" + " select book.*,rownum r from ( "
		 + " select * from books " + ")book where rownum <= "
		 + (page_NO * page_books) + ")temp" + " where temp.r > "
		 + ((page_NO - 1) * page_books);
		 mysql 分页
		 String sql = "select top " + page_books + " * " + " from books "
		 + " where bid not in " + " ( " + " select top " + page_books
		 * (page_NO - 1) + " bid form books order by bid " + " ) "
		 + " order by bid";
		 mysql 分页
		 当前页
		 查询第11到第15条数据
		 select * from table_name limit 10,5
		 */
		int now_page = (page_NO - 1) * page_goods;
		String mysql = "select * from e_goods limit " + now_page + " , "
				+ page_goods;
		return goodsdao.query(mysql);
	}

	/*
	 * 按照商品名称查找类似商品
	 */
	@Override
	public List<Goods> findGoodsLikeName(String goodsname) {
		String sql = "select * from e_goods where text like '%" + goodsname
				+ "%'";
		return goodsdao.query(sql);
	}

	/*
	 * 返回商品数量
	 */
	@Override
	public int count() {

		return goodsdao.count();
	}

	/*
	 * 修改商品库存
	 */
	@Override
	public boolean changeStock(String gid, String change_count) {

		String sql = "update e_goods set stock = stock+" + change_count
				+ " where goodsid = " + gid;
		;
		return goodsdao.update(sql) > 0 ? true : false;
	}
	@Override
	public boolean goodsExists(String productname) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int setGoodsId() {
		// TODO Auto-generated method stub
		return goodsdao.setGoodsId();
	}


}
