package cc.openhome.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import cc.openhome.model.DBInterface;


@WebServlet(urlPatterns= {"/book/in"},
			initParams= {@WebInitParam(name="FAIL",value="../book")}
			)
public class Book_in extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
    private String FAIL;
	private List<String> list;
	private static String LOCATION="../bookpic/";
	@Override
	public void init() throws ServletException{
		FAIL=getServletConfig().getInitParameter("FAIL");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			out.close();
			response.sendRedirect(FAIL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			out.close();
			response.sendRedirect(FAIL);
		}
	}
	
	private void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException, NumberFormatException, SQLException {
		String id=request.getParameter("id");
		if(id==null||id.isEmpty()) {
			response.sendRedirect(FAIL);
			return;
		}
		DBInterface dbInterface=(DBInterface)getServletContext().getAttribute("dbInterface");
		list=dbInterface.getBook(Long.parseLong(id));
		//{書名，價錢，賣家，描述}
		//dbInterface.Close();
		if(list.isEmpty()) {
			response.sendRedirect(FAIL);
			return;
		}
		response.setContentType("text/html;charset=UTF-8");
		out=response.getWriter();
		//head
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"\r\n" + 
				"<head>\r\n" + 
				"\r\n" + 
				"    <style>\r\n" + 
				"        \r\n" + 
				"        body {\r\n" + 
				"            background-color: #BBFFEE;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .content {\r\n" + 
				"            //background-color: lightblue;\r\n" + 
				"            //height: 600px;\r\n" + 
				"            //width: 100%;\r\n" + 
				"            overflow: auto;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        #title ul li {\r\n" + 
				"            display: inline;\r\n" + 
				"        }\r\n" + 
				"    </style>\r\n" + 
				"</head>");
		//head結束
		//body開始
		out.println("<body>");
		out.println("<h1 style=\"text-align: center;\">"+list.get(0)+"</h1>");
		out.println("<div>");
		out.println("<div style=\"float:right;font-size:25px;\">"+"售價:"+list.get(1)+"元</div>");
		out.println("<div style=\"font-size:30px;\">賣家:"+list.get(2)+"</div>");
		out.println("</div>");
		//out.println("<div class=\"content\">");
		out.println("描述:<br>");
		out.println("<h2>"+tranRNtoBR(list.get(3))+"</h2>");
		//放圖
		out.println("<div>");
		out.println("<img src='"+LOCATION+Long.parseLong(id)+"_1.jpg' alt=''/><br>");
		out.println("<img src='"+LOCATION+Long.parseLong(id)+"_2.jpg' alt=''/><br>");
		out.println("<img src='"+LOCATION+Long.parseLong(id)+"_3.jpg' alt=''/><br>");
		//out.println("</div>");
	    //放圖結束
		out.println("</div>\r\n" +  
				"</body>\r\n" + 
				"</html>");
        out.close();
	
	}
	
	private void getPic(long pic) {
		
	}
	
	private String tranRNtoBR(String origin) {
		String[] seper=origin.split("\n");
		String re="";
		for(int i=0;i<seper.length;i++) {
			re+=seper[i]+"<br>";
		}
		return re;
	}
	/*out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
	out.println("<html>");
    out.println("<head>");
    out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
    out.println("<title>book_in</title>");
    out.println("</head>");
    out.println("<body>");
    out.println(list.size()+"<br>");
    out.println("物品名: "+list.get(0)+"<br>");
    out.println("價錢: "+list.get(1)+"<br>");
    out.println("賣家: "+list.get(2)+"<br>");
    out.println("描述: "+list.get(3)+"<br>");
    out.println("<div>");
    out.println("<img src='C:/workspace/DringLow/picture/book/"+Long.parseLong(id)+".jpg' alt='"+Long.parseLong(id)+"'/>");
    out.println("</div>");
    out.println("</body>");*/
}
