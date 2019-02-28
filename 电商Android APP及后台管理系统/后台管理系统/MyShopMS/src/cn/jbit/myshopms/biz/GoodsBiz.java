package cn.jbit.myshopms.biz;

import java.util.List;

import cn.jbit.myshopms.dao.GoodsDao;
import cn.jbit.myshopms.entity.Goods;

public interface GoodsBiz {
	public List<Goods> findAllGoods(int page_goods, int page_NO );
	public List<Goods> findGoodsLikeName(String goodsname);
	public boolean addGoods(Goods goods);
	public GoodsDao getGoodsdao();
	public void setGoodsdao(GoodsDao goodsdao);
	public int count();
	public int setGoodsId();
	public boolean changeStock(String gid, String change_count);
	public boolean goodsExists(String productname);

}
