package cn.jbit.myshopms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class BaseDao {

	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	private final static String DRIVERNAME = "com.mysql.jdbc.Driver";
	private final static String USERNAEM = "root";
	private final static String DATABASE = "e_commerce_db";
	private final static String PASSWORD = "123456";

	/*
	 * 获取数据库连接
	 */
	protected void openconnection() {

		try {
			Class.forName(DRIVERNAME);
			String url = "jdbc:mysql://localhost:3306/" + DATABASE + "?user="
					+ USERNAEM + "&password=" + PASSWORD
					+ "&useUnicode=true&characterEncoding=UTF8";
			con = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 执行更新或者删除操作
	 */
	protected int deleteOrUpdate(String sql) {

		int row = 0;
		openconnection();
		try {
			ps = con.prepareStatement(sql);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}
		return row;
	}

	/*
	 * 插入数据
	 */
	protected int insert(String table, List<?> list) {

		int row = 0;
		StringBuffer sql = null;
		if (table.equals("a_user")||table.equals("e_goods"))
			sql = new StringBuffer("insert into " + table + " values(");
		else {
			sql = new StringBuffer("insert into " + table + " values("
					+ list.get(0) + ",");
			list.remove(0);
		}
		for (int i = 0; i < list.size(); i++) {
			if (i + 1 == list.size()) {
				sql.append("?)");
				break;
			}
			sql.append("?,");
		}
		openconnection();
		try {
			ps = con.prepareStatement(sql.toString());
			for (int i = 0; i < list.size(); i++)
				ps.setObject(i + 1, list.get(i));
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}
		return row;
	}

	/*
	 * 执行sql查询
	 */
	protected List<Map> query(String sql, String[] columns) {

		List<Map> list = new ArrayList<Map>();
		Map<String, Object> map = null;
		openconnection();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				map = new HashMap<String, Object>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getObject(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			if (e.getMessage().equals("列名无效"))
				System.out.println("+++++++++++++++当前要查找的列不存在！+++++++++++++++");
			else
				e.printStackTrace();
		} finally {
			closeResource();
		}
		return list;
	}

	/*
	 * 关闭资源
	 */
	protected boolean closeResource() {

		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
