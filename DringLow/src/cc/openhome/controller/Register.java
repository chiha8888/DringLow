package cc.openhome.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.CycleRecoverable.Context;

import cc.openhome.model.DBInterface;
import cc.openhome.model.UserService;

import java.util.*;
import java.io.*;


@WebServlet(urlPatterns= { "/register.do"},
			initParams= {
					@WebInitParam(name="SUCCESS",value="REGISTERSUCCESS.html"),
					@WebInitParam(name="ERROR",value="REGISTERFAIL.html")
			}
			)
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private String SUCCESS;
    private String FAIL;
    
    @Override
    public void init() throws ServletException{
    	SUCCESS=getServletConfig().getInitParameter("SUCCESS");
    	FAIL=getServletConfig().getInitParameter("ERROR");
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		DBInterface dbInterface=(DBInterface)getServletContext().getAttribute("dbInterface");
		boolean success=false;
		try {
			success=dbInterface.userExist(email);
		} catch (Exception e) {
			response.sendRedirect(FAIL);
			return;
		}
		if(success) {	//已經存在
			response.sendRedirect(FAIL);
			return;
		}
		
		try {	//加入資料庫
			success=dbInterface.addUser(email, email, password);
		} catch (SQLException e) {
			response.sendRedirect(FAIL);
			return;
		}
		if(success==false) {
			response.sendRedirect(FAIL);
			return;
		}
		else {
			response.sendRedirect(SUCCESS);
		}
		
	}
	
	private boolean isInvalidEmail(String email) {
		return email==null||!email.matches("^[_a-z0-9-]+([.]" + "[_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
	}
	private boolean isInvalidPassword(String password,String confirmedPasswd) {
		return password==null||password.length()<6||password.length()>16||!password.equals(confirmedPasswd);
	}
	
}
