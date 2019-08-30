package cc.openhome.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.DBInterface;

/**
 * Servlet implementation class Stuff
 */
@WebServlet(urlPatterns= {"/stuff"}
			)
public class Stuff extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<List<String>> list;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private void processRequest(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, SQLException, IOException {
		String tag=request.getParameter("tag");
		if(tag.isEmpty()||tag==null) {
			tag="999";
		}
		
		DBInterface dbInterface=(DBInterface)getServletContext().getAttribute("dbInterface");
		list=dbInterface.getStuffs(Integer.parseInt(tag));
		//{id,物品名，價錢，賣家}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		out.println("<html>");
        out.println("<head>");
        out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
        out.println("<title>book</title>");
        out.println("</head>");
        out.println("<body>");
		for(List<String> l:list) {
			out.println("<a href='stuff/in?id="+l.get(0)+"'>go </a>");
			out.println(l.get(1)+" "+l.get(2)+" "+l.get(3)+"<br>");
			
			out.println("<br>");
		}
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
