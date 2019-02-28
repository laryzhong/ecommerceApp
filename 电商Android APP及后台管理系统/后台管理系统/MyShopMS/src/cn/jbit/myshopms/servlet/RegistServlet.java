package cn.jbit.myshopms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jbit.myshopms.biz.AdminUserBiz;
import cn.jbit.myshopms.biz.impl.AdminUserBizImpl;
import cn.jbit.myshopms.dao.AdminUserDao;
import cn.jbit.myshopms.dao.impl.AdminUserDaoImpl;
import cn.jbit.myshopms.entity.AdminUserInfo;
import cn.jbit.myshopms.util.RandomTool;


/*
 * ע��
 */
public class RegistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AdminUserBiz adminuserbiz = null;
	private AdminUserDao adminuserdao = null;

	@Override
	public void init() throws ServletException {
		
		adminuserdao = new AdminUserDaoImpl();
		adminuserbiz = new AdminUserBizImpl();
		adminuserbiz.setAdminUserdao(adminuserdao);
	}
	
	
	private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("utf-8");
		String adminName = req.getParameter("adminName");
		//����û����Ƿ��Ѵ���
		boolean isUserExist = adminuserbiz.userExists(adminName);
		resp.setCharacterEncoding("utf-8");
		if(isUserExist){			
			resp.getWriter().write("true");
			resp.getWriter().close();
		}else{
			resp.getWriter().write("false");
			resp.getWriter().close();
		}
	}
	/*
	 * ע�Ṧ��
	 */
	private void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("utf-8");
		String adminName = new String(req.getParameter("adminName").getBytes("ISO8859-1"),"utf-8");
		String adminPassword = new String(req.getParameter("adminPassword").getBytes("ISO8859-1"),"utf-8");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		AdminUserInfo adminuserinfo = new AdminUserInfo();
		adminuserinfo.setAdminId(RandomTool.getTimeMillisToString());
		adminuserinfo.setAdminName(adminName);
		adminuserinfo.setAdminPassword(adminPassword);
		adminuserinfo.setAdminLastLoginTime(df.format(System.currentTimeMillis()));
		
		if(!adminuserbiz.addAminUser(adminuserinfo)){
			resp.setHeader("Content-type", "text/html;charset=UTF-8");  
			resp.setCharacterEncoding("utf-8");
			PrintWriter pw = resp.getWriter();			
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert(\"���ʧ�ܣ���������ӣ�\");");
			pw.println("open(\"login.jsp\",\"_self\");");
			pw.println("</script>");
			
			return;
		}		
		resp.sendRedirect("register_success.html");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//�鿴��ǰִ�еĶ�����ʲô(check������û�����null��ע��)
		String action = req.getParameter("action");
		if(action==null)
			regist(req, resp);
		else
			check(req, resp);	
	}
}
