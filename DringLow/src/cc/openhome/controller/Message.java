package cc.openhome.controller;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jrockit.jfr.RequestableEvent;
import com.sun.xml.internal.ws.resources.AddressingMessages;

import cc.openhome.model.UserService;


@WebServlet(
		urlPatterns= {"/message.do"},
		initParams= {
				@WebInitParam(name="SUCCESS_VIEW",value="member.view"),
				@WebInitParam(name="ERROR_VIEW",value="member.view")
		}
		)
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String SUCCESS_VIEW;
	private String ERROR_VIEW;
	
	@Override
	public void init() throws ServletException{
		SUCCESS_VIEW=getServletConfig().getInitParameter("SUCCESS_VIEW");
		ERROR_VIEW=getServletConfig().getInitParameter("ERROR_VIEW");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String blabla=request.getParameter("blabla");
		if(blabla!=null&&blabla.length()!=0) {
			if(blabla.length()<=140&&blabla.length()>=10) {
				UserService userService=(UserService)getServletContext().getAttribute("userService");
				userService.addMessage((String)request.getSession().getAttribute("login"),blabla);
				response.sendRedirect(SUCCESS_VIEW);
			}
			else {
				request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
			}
		}
		else {
			response.sendRedirect(ERROR_VIEW);
		}
		
	}
	
}
