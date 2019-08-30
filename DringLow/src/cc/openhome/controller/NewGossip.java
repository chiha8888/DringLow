package cc.openhome.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cc.openhome.model.DBInterface;

/**
 * Servlet implementation class NewGossip
 */
@WebServlet(urlPatterns= {"/gossip/message/po"},
			initParams= {@WebInitParam(name="FAIL",value="../../gossip"),
						@WebInitParam(name="SUCCESS",value="../../gossip")}
			)
public class NewGossip extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String FAIL;
	private String SUCCESS;
	
	@Override
	public void init() throws ServletException{
		FAIL=getServletConfig().getInitParameter("FAIL");
		SUCCESS=getServletConfig().getInitParameter("SUCCESS");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("login");
		DBInterface dbInterface=(DBInterface)getServletContext().getAttribute("dbInterface");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		long id=new Date().getTime();
		boolean success=false;
		try {
			success=dbInterface.addGossip(id, title, content, username);
			if(success==false) {
				response.sendRedirect(FAIL);
				return;
			}
		} catch (SQLException e) {
			response.sendRedirect(FAIL);
			return;
		}
		//成功
		response.sendRedirect(SUCCESS);
		return;
	}
	
}
