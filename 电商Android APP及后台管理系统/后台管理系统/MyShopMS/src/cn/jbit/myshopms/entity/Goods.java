package cn.jbit.myshopms.entity;

public class Goods {

	private int gid = 0;
	private String goodsname = null;
	private String price = null;
	private String image = null;
	private String content = null;
	private int stock = 0;
	private int count = 0;
	private int sortId = 0;
	private int spanSize = 0;
	public int getSpanSize() {
		return spanSize;
	}
	public void setSpanSize(int spanSize) {
		this.spanSize = spanSize;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
