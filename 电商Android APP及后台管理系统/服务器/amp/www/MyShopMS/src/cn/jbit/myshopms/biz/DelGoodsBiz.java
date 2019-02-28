package cn.jbit.myshopms.biz;
import cn.jbit.myshopms.entity.Goods;


public interface DelGoodsBiz {
	public boolean delGoods(Goods goods);
	public int count();
}
