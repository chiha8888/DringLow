package cc.openhome.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cc.openhome.model.DBInterface;

/**
 * Servlet implementation class GossipReply
 */
@WebServlet(urlPatterns= {"/gossip/message/re"},
			initParams= {@WebInitParam(name="SUCCESS",value="../../gossip"),
						@WebInitParam(name="FAIL",value="../../gossip")}
			)
public class GossipReply extends HttpServlet {
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
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect(FAIL);
			return;
		}

	}
	
	private void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
		DBInterface dbInterface=(DBInterface)getServletContext().getAttribute("dbInterface");
		HttpSession session=request.getSession();
		boolean success=false;
		try {
			String id=request.getParameter("id");
			String comment=request.getParameter("comment");
			String username=(String)session.getAttribute("login");
			success=dbInterface.addGossipReply(Long.parseLong(id), comment, username);
			if(success==false) {
				response.sendRedirect(FAIL);
				return;
			}
		} catch (Exception e) {
			response.sendRedirect(FAIL);
			return;
		}
		//成功
		response.sendRedirect(SUCCESS);
		return;
	}
}
