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
		JFrame frame = new JFrame("��Ʒ����ͳ��");
		frame.setLayout(new GridLayout(2, 2, 10, 10));
		frame.add(new BarChart().getChartPanel());// �������ͼ ��Ʒ�������
		frame.add(new PieChart().getChartPanel());// ��ӱ�״ͼ �����������
		frame.setBounds(50, 50, 800, 600);
		frame.setVisible(true);
		resp.sendRedirect("goods");
	}
}

class BarChart extends BaseDao {
	ChartPanel frame1;

	public BarChart() {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D("��Ʒ�������", // ͼ�����
				"��Ʒ����", // Ŀ¼�����ʾ��ǩ
				"����", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
				);
		// �����￪ʼ
		CategoryPlot plot = chart.getCategoryPlot();// ��ȡͼ���������
		CategoryAxis domainAxis = plot.getDomainAxis(); // ˮƽ�ײ��б�
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // ˮƽ�ײ�����
		domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // ��ֱ����
		ValueAxis rangeAxis = plot.getRangeAxis();// ��ȡ��״
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������

		// �������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������

		frame1 = new ChartPanel(chart, true); // ����Ҳ������chartFrame,����ֱ������һ��������Frame

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
		JFreeChart chart = ChartFactory.createPieChart3D("�����������", data, true,
				false, false);
		// ���ðٷֱ�
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// ���һ��DecimalFormat������Ҫ������С������
		NumberFormat nf = NumberFormat.getNumberInstance();// ���һ��NumberFormat����
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator(
				"{0}  {2}", nf, df);// ���StandardPieSectionLabelGenerator����
		pieplot.setLabelGenerator(sp1);// ���ñ�ͼ��ʾ�ٷֱ�

		// û�����ݵ�ʱ����ʾ������
		pieplot.setNoDataMessage("��������ʾ");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// ���ò���ʾ��ֵ
		pieplot.setIgnoreZeroValues(true);// ���ò���ʾ��ֵ
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������
		PiePlot piePlot = (PiePlot) chart.getPlot();// ��ȡͼ���������
		piePlot.setLabelFont(new Font("����", Font.BOLD, 10));// �������
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 10));
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
