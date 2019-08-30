package cc.openhome.view;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.util.Comparator;
import java.util.Locale;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.javafx.sg.prism.web.NGWebView;

import cc.openhome.model.UserService;

/**
 * Servlet implementation class Member
 */
@WebServlet("/member.view")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String LOGIN="index.html";

    @Override   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);	
	}
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		processRequest(request,response);
	}
	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		String username=(String)request.getSession().getAttribute("login");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		out.println("<html>");
        out.println("<head>");
        out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
        out.println("<title>Gossip 微網誌</title>");
        out.println("<link rel='stylesheet' href='css/member.css' type='text/css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='leftPanel'>");
        out.println("<img src='images/caterpillar.jpg' alt='DringLow' /><br><br>");
        out.println("<a href='logout.do?username=" + username + "'>登出 " + username + "</a>");
        out.println("</div>");
        out.println("<form method='post' action='message.do'>");
        out.println("分享新鮮事...<br>");
        
        String blabla=request.getParameter("blabla");
        if(blabla==null) 
        	blabla="";
        else
        	out.println("信息要在10~140字以內<br>");
        out.println("<textarea cols='60' rows='4' name='blabla'>" + blabla + "</textarea>");
        out.println("<br>");
        out.println("<button type='submit'>送出</button>");
        out.println("</form>");
        out.println("<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>");
        out.println("<thead>");
        out.println("<tr><th><hr></th></tr>");
        out.println("</thead>");
        out.println("<tbody>");
        
        UserService userService=(UserService)getServletContext().getAttribute("userService");
        TreeMap<Date, String> messages=userService.readMessages();
        DateFormat dateFormat=DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL,Locale.TAIWAN);
       
        for(Date date:messages.keySet()) {
        	out.println("<tr><td style='vertical-align: top;'>");
            //out.println(username + "<br>");
            out.println(messages.get(date) + "<br>");
            out.println(dateFormat.format(date));
            //out.println("<a href='delete.do?message=" + date.getTime() + "'>刪除</a>");
            out.println("<hr></td></tr>");
        }
        
        out.println("</tbody>");
        out.println("</table>");
        out.println("<hr style='width: 100%; height: 1px;'>");
        out.println("</body>");
        out.println("</html>");

        out.close();
	}
	
	
	
	
}
