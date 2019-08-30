package cc.openhome.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cc.openhome.model.DBInterface;

/**
 * Servlet implementation class Appraise
 */
@WebServlet(urlPatterns= {"/course/message/re"},
			initParams= {@WebInitParam(name="SUCCESS",value="../in"),
						@WebInitParam(name="FAIL",value="../in")}
			)
public class Appraise extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String SUCCESS;
	private String FAIL;
	
	@Override
	public void init() throws ServletException{
		SUCCESS=getServletConfig().getInitParameter("SUCCESS");
		FAIL=getServletConfig().getInitParameter("FAIL");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(FAIL);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		long id=Long.parseLong(request.getParameter("id"));
		int teacher=Integer.parseInt(request.getParameter("teacher"));
		int sweet=Integer.parseInt(request.getParameter("sweet"));
		int rich=Integer.parseInt(request.getParameter("rich"));
		String appraise=request.getParameter("appraise");
		String username=(String)session.getAttribute("login");
		
		DBInterface dbInterface=(DBInterface)getServletContext().getAttribute("dbInterface");
		boolean success=false;
		try {
			success=dbInterface.addAppraise(id, teacher, sweet, rich, appraise, username);
		} 
		catch (SQLException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(success) {
			response.sendRedirect(SUCCESS+"?id="+id);
		}
		else {
			response.sendRedirect(FAIL+"?id="+id);
		}
		
	}

}
