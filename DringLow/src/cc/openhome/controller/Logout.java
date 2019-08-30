package cc.openhome.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet(urlPatterns= { "/logout.do"},
			initParams= {@WebInitParam(name="SUCCESS",value="LOGOUTSUCCESS.html"),
						@WebInitParam(name="FAIL",value="LOGOUTFAIL.html")}
			)
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String SUCCESS;
    private String FAIL;
	
	@Override
	public void init() throws ServletException{
		SUCCESS=getServletConfig().getInitParameter("SUCCESS");
		FAIL=getServletConfig().getInitParameter("FAIL");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		response.sendRedirect(SUCCESS);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
