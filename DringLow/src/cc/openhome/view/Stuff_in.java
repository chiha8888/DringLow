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

import cc.openhome.model.DBInterface;

/**
 * Servlet implementation class Stuff_in
 */
@WebServlet(urlPatterns= {"/stuff/in"},
			initParams= {@WebInitParam(name="FAIL",value="../stuff")}
			)
public class Stuff_in extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private List<String> list;
    private String FAIL;

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
		// TODO Auto-generated method stub
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
		list=dbInterface.getStuff(Long.parseLong(id));
		//{物品名，價錢，賣家，描述}
		//dbInterface.Close();
		if(list.isEmpty()) {
			response.sendRedirect(FAIL);
			return;
		}
		
		response.setContentType("text/html;charset=UTF-8");
		out=response.getWriter();
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		out.println("<html>");
        out.println("<head>");
        out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
        out.println("<title>book_in</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("物品名: "+list.get(0)+"<br>");
        out.println("價錢: "+list.get(1)+"<br>");
        out.println("賣家: "+list.get(2)+"<br>");
        out.println("描述: "+list.get(3)+"<br>");
        
        out.println("</body>");
        out.close();
	}
	
	
}
