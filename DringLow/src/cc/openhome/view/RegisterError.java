package cc.openhome.view;


import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register/error.view")
public class RegisterError extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out=response.getWriter();
    	out.println("<!DOCTYPE html PUBLIC "+"'-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
    	out.println("<html>");
    	out.println("<head>");
    	out.println("<meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
    	out.println("<title>增新會員失敗</title>");
    	out.println("</head>");
    	out.println("<body>");
    	out.println("<h1>增新會員失敗</h1>");
    	out.println("<ul style='color:rgb(255,0,0);'>");
    	List<String> errors=(List<String>) request.getAttribute("errors");
    	for(String error : errors) {
    		out.println("<li>"+error+"</li>");
    	}
    	out.println("</ul>");
    	out.println("<a href='register.html'>返回註冊頁面</a>");
    	out.println("</body>");
    	out.println("</html>");
    	out.close();
    }
    
}
