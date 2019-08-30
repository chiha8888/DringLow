package cc.openhome.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Data;

import cc.openhome.model.DBInterface;


@MultipartConfig(location="C:/workspace/DringLow/WebContent/picture/book")
@WebServlet(urlPatterns= {"/salebook.do"},
			initParams= {@WebInitParam(name="FAIL",value="book"),
						@WebInitParam(name="SUCCESS",value="book")}
		)
public class SaleBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String FAIL;
	private String SUCCESS;
	//private static String LOCATION="picture/book/";
	@Override
	public void init() throws ServletException{
		FAIL=getServletConfig().getInitParameter("FAIL");
		SUCCESS=getServletConfig().getInitParameter("SUCCESS");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(FAIL);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBInterface dbInterface=(DBInterface)getServletContext().getAttribute("dbInterface");
		boolean success=false;
		try {
			String bookname=request.getParameter("bookname");
			int price=Integer.parseInt(request.getParameter("price"));
			String describe=request.getParameter("describe");
			int tag=Integer.parseInt(request.getParameter("tag"));
			HttpSession session=request.getSession();
			String username=(String)session.getAttribute("login");
			
			long id=new Date().getTime();
			int count=1;
			for(Part part:request.getParts()) {
				if(part.getName().startsWith("pic")) {
					if(part.getSubmittedFileName()==null||part.getSubmittedFileName()=="") {
						continue;
					}
					String filename=Long.toString(id)+"_"+Integer.toString(count++)+".jpg";
					part.write(filename);
				}
			}
			
			success=dbInterface.addBook(id, bookname, price, describe, tag, username);
			
			if(success==false) {
				response.sendRedirect(FAIL);
				return;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect(FAIL);
			return;
		}
		
		response.sendRedirect(SUCCESS);
		
	}
	private String getFilename(Part part) {
		String header=part.getHeader("Content-Disposition");
		String filename=header.substring(header.lastIndexOf("\\")+1, header.lastIndexOf("\""));
		return filename;
	}

}
