package cc.openhome.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(location="C:/workspace/DringLow/picture/stuff")
@WebServlet(urlPatterns= {"/salestuff.do"}
			)
public class SaleStuff extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s="";
		for(Part part:request.getParts()) {
			if(part.getName().startsWith("pic")) {
				if(part.getSubmittedFileName()==null||part.getSubmittedFileName()=="") {
					continue;
				}
				String filename=getFilename(part);
				part.write(filename);
			}
		}
		s+=request.getParameter("name");
		s+=" ";
		s+=request.getParameter("price");
		s+=" ";
		s+=request.getParameter("describe");
		s+=" ";
		
	}
	
	private String getFilename(Part part) {
		String header=part.getHeader("Content-Disposition");
		String filename=header.substring(header.lastIndexOf("\\")+1, header.lastIndexOf("."));
		return filename+".jpg";
	}

}
