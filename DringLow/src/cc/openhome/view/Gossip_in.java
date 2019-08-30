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


@WebServlet(urlPatterns= {"/gossip/in"},
			initParams= {@WebInitParam(name="FAIL",value="../gossip")}
			)
public class Gossip_in extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PrintWriter out;
    private List<String> up;
    private List<List<String>> down;
    private String FAIL;
    
    @Override
    public void init() throws ServletException{
    	FAIL=getServletConfig().getInitParameter("FAIL");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException, NumberFormatException, SQLException {
		String id=request.getParameter("id");
		if(id==null||id.isEmpty()) {
			response.sendRedirect(FAIL);
			return;
		}
		
		DBInterface dbInterface=(DBInterface)getServletContext().getAttribute("dbInterface");
		up=dbInterface.getGossip(Long.parseLong(id));
		//{title,文章內容，發文者}
		down=dbInterface.getGossipReply(Long.parseLong(id));
		//{人，留言}，{人，留言}，{人，留言}...........
		if(up.isEmpty()) {
			response.sendRedirect(FAIL);
			return;
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		out.println("<html>");
		out.println("<head>");
        out.println("<meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
		out.println("<title>"+up.get(0)+" - DringLow</title>");
        out.println("</head>");
		out.println("<body>");
		out.println(up.get(0)+" "+up.get(1)+" "+up.get(2)+"<br>");
		for(List<String> l:down) {
			
		}
		out.println("</body>");
		out.println("</html>");
	}
}
