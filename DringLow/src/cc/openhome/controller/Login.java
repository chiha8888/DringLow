package cc.openhome.controller;

import java.io.*;
import java.nio.file.attribute.UserPrincipalLookupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.Name;

import cc.openhome.model.DBInterface;
import cc.openhome.model.UserService;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class Login
 */
@WebServlet(
		urlPatterns= {"/login.do"},
		initParams= {
				@WebInitParam(name="SUCCESS",value="LOGINSUCCESS.html"),
				@WebInitParam(name="FAIL",value="LOGINFAIL.html")
					}
			)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private String SUCCESS;
    private String FAIL;
    
    @Override
    public void init() throws ServletException{
    	SUCCESS=getServletConfig().getInitParameter("SUCCESS");
    	FAIL=getServletConfig().getInitParameter("FAIL");
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		DBInterface dbInterface=(DBInterface)getServletContext().getAttribute("dbInterface");
		boolean success=false;
		try {
			success=dbInterface.verifyUser(email, password);
		} catch (Exception e) {
			response.sendRedirect(FAIL);
			return;
		}
		if(success==false) {
			response.sendRedirect(FAIL);
			return;
		}
		else {	//成功
			request.getSession().setAttribute("login", email);
			response.sendRedirect(SUCCESS);
			return;
		}
		
	}
	
}
