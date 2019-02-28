package cn.jbit.myshopms.entity;

public class Order {

	private String oid = null;
	private String username = null;
	private int count = 0;
	private String orderTime = null;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOid() {
		return oid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
