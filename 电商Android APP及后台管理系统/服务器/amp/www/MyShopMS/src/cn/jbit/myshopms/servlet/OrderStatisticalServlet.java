package cn.jbit.myshopms.servlet;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import cn.jbit.myshopms.biz.OrderStaticalBiz;
import cn.jbit.myshopms.biz.UserCartBiz;
import cn.jbit.myshopms.biz.impl.OrderStaticalBizImpl;
import cn.jbit.myshopms.biz.impl.UserCartBizImpl;
import cn.jbit.myshopms.dao.BaseDao;
import cn.jbit.myshopms.dao.OrderStaticalDao;
import cn.jbit.myshopms.dao.UserCartDao;
import cn.jbit.myshopms.dao.impl.OrderStaticalDaoImpl;
import cn.jbit.myshopms.dao.impl.UserCartDaoImpl;
import cn.jbit.myshopms.util.PageTools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;

@SuppressWarnings({ "serial", "unused" })
public class OrderStatisticalServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("商品销量统计");
		frame.setLayout(new GridLayout(2, 2, 10, 10));
		frame.add(new BarChart().getChartPanel());// 添加柱形图 单品销量情况
		frame.add(new PieChart().getChartPanel());// 添加饼状图 分类销量情况
		frame.setBounds(50, 50, 800, 600);
		frame.setVisible(true);
		resp.sendRedirect("goods");
	}
}

class BarChart extends BaseDao {
	ChartPanel frame1;

	public BarChart() {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D("单品销量情况", // 图表标题
				"商品种类", // 目录轴的显示标签
				"数量", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
				);
		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		// 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

		frame1 = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame

	}

	@SuppressWarnings({ "rawtypes" })
	private static CategoryDataset getDataSet() {
		OrderStaticalDao orderdao = new OrderStaticalDaoImpl();
		String sql = "select * "
				+ " from e_order_list , e_goods , e_sort_lists "
				+ " where e_order_list.goodsId = e_goods.goodsId and "
				+ " e_goods.sortId = e_sort_lists.sortId ";
		List<Map> orderStatical = orderdao.queryOrder(sql);
		// List orderStatical = orderbiz.OrderStatical();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// System.out.println(orderStatical.get(0).get("text"));
		for (int i = 0; i < orderStatical.size(); i++) {
			String text = orderStatical.get(i).get("text").toString();
			int count = Integer.parseInt(orderStatical.get(i).get("count")
					.toString());
			String sortName = orderStatical.get(i).get("sortName").toString();
			dataset.addValue(count, sortName, text);
			// System.out.println(text + " " + count + " " +sortName);
		}
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return frame1;

	}

}

@SuppressWarnings("rawtypes")
class PieChart extends BaseDao {
	ChartPanel frame1;

	public PieChart() {
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("分类销量情况", data, true,
				false, false);
		// 设置百分比
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// 获得一个DecimalFormat对象，主要是设置小数问题
		NumberFormat nf = NumberFormat.getNumberInstance();// 获得一个NumberFormat对象
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator(
				"{0}  {2}", nf, df);// 获得StandardPieSectionLabelGenerator对象
		pieplot.setLabelGenerator(sp1);// 设置饼图显示百分比

		// 没有数据的时候显示的内容
		pieplot.setNoDataMessage("无数据显示");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// 设置不显示空值
		pieplot.setIgnoreZeroValues(true);// 设置不显示负值
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
		PiePlot piePlot = (PiePlot) chart.getPlot();// 获取图表区域对象
		piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));// 解决乱码
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));
	}

	private static DefaultPieDataset getDataSet() {
		OrderStaticalDao orderdao = new OrderStaticalDaoImpl();
		String sql = "select * "
				+ " from e_order_list , e_goods , e_sort_lists "
				+ " where e_order_list.goodsId = e_goods.goodsId and "
				+ " e_goods.sortId = e_sort_lists.sortId ";
		List<Map> orderStatical = orderdao.queryOrder(sql);
		// List orderStatical = orderbiz.OrderStatical();
		DefaultPieDataset dataset = new DefaultPieDataset();

		// System.out.println(orderStatical.get(0).get("text"));
		for (int i = 0; i < orderStatical.size(); i++) {
			int count = Integer.parseInt(orderStatical.get(i).get("count")
					.toString());
			String sortName = orderStatical.get(i).get("sortName").toString();
			dataset.setValue(sortName, count);
			// System.out.println(text + " " + count + " " +sortName);
		}
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return frame1;

	}
}
