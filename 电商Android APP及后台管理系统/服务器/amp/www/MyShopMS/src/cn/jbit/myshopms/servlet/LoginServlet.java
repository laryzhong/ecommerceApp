package cn.jbit.myshopms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jbit.myshopms.biz.AdminUserBiz;
import cn.jbit.myshopms.biz.impl.AdminUserBizImpl;
import cn.jbit.myshopms.dao.AdminUserDao;
import cn.jbit.myshopms.dao.impl.AdminUserDaoImpl;

/*
 * µÇÂ¼
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	private AdminUserBiz userbiz = null;
	private AdminUserDao userdao = null;

	@Override
	public void init() throws ServletException {
		
		userdao = new AdminUserDaoImpl();
		userbiz = new AdminUserBizImpl();
		userbiz.setAdminUserdao(userdao);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String username = req.getParameter("adminName");
		String password = req.getParameter("adminPassword");
		//µÇÂ¼²Ù×÷
		boolean canLogin = userbiz.checkLogin(username, password);
		//µÇÂ½³É¹¦
		if(canLogin){
			req.getSession().setAttribute("loginuser", username);
			resp.sendRedirect("goods");
		}else{
			//µÇÂ¼Ê§°Ü
			resp.setHeader("Content-type", "text/html;charset=UTF-8");  
			resp.setCharacterEncoding("utf-8");
			PrintWriter pw = resp.getWriter(); 
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert(\"µÇÂ¼Ê§°Ü£¡ÇëÖØÐÂµÇÂ¼£¡£¡\");");
			pw.println("open(\"login.jsp\",\"_self\");");
			pw.println("</script>");
			pw.close();
		}
	}
}
