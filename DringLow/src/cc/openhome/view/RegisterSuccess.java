package cc.openhome.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterSuccess
 */
@WebServlet("/register/success.view")
public class RegisterSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html PUBLIC "+"'-//W3C//DTD HTML 4.01 Transitional//EN'>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta content='text/html; charset=UTF-8' http-equiv='content-type'");
		out.println("<title>註冊成功</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>會員"+request.getParameter("username")+"註冊成功</h1>");
		out.println("<a href='index.html'>回首頁</a>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
	
}
