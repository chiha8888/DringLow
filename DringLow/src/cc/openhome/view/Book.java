package cc.openhome.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.jws.soap.InitParam;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.hibernate.FabricMultiTenantConnectionProvider;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import cc.openhome.model.DBInterface;
import java.util.*;
/**
 * Servlet implementation class Book
 */
@WebServlet(urlPatterns= {"/book"}
			)
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<List<String>> list;
	private static String LOCATION="bookpic/";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException, NumberFormatException, SQLException {
		
		String tag=request.getParameter("tag");
		if(tag.isEmpty()||tag==null) {
			tag="999";
		}
	
		DBInterface dbInterface=(DBInterface)getServletContext().getAttribute("dbInterface");
		list=dbInterface.getBooks(Integer.parseInt(tag));
		//{id，書名，價錢，賣家},{id，書名，價錢，賣家},{id，書名，價錢，賣家}.........
		//dbInterface.Close();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<html>\r\n" + 
				"<head>\r\n" + 
				"    <title>課用二手書 - DringLow</title>\r\n" + 
				"    <meta charset=\"utf-8\">\r\n" + 
				"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" charset=\"utf-8\">\r\n" + 
				"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\" charset=\"utf-8\"></script>\r\n" + 
				"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" charset=\"utf-8\"></script>\r\n" + 
				"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js\" charset=\"utf-8\"></script>\r\n" + 
				"    <style>\r\n" + 
				"        .title {\r\n" + 
				"            margin: 0;\r\n" + 
				"            background: lightgrey;\r\n" + 
				"            border: 0;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        li:last-child {\r\n" + 
				"            border-right: none;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .Horizontalitem {\r\n" + 
				"            display: block;\r\n" + 
				"            color: white;\r\n" + 
				"            text-align: center;\r\n" + 
				"            padding: 10px 15px;\r\n" + 
				"            text-decoration: none;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .Horizontalitem:hover:not(.active) {\r\n" + 
				"            background-color: green;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .active.Horizontalitem {\r\n" + 
				"            background-color: #4CAF50;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .Horizontalbar {\r\n" + 
				"            list-style-type: none;\r\n" + 
				"            margin: 0;\r\n" + 
				"            padding: 0;\r\n" + 
				"            background-color: black;\r\n" + 
				"            overflow: hidden;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .Horizontalborder {\r\n" + 
				"            float: left;\r\n" + 
				"            border-right: 1px solid #bbb;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .Verticalbar {\r\n" + 
				"            list-style-type: none;\r\n" + 
				"            background-color: #f1f1f1;\r\n" + 
				"            width: 150px;\r\n" + 
				"            margin-top: 20px;\r\n" + 
				"            padding: 0;\r\n" + 
				"            position: fixed;\r\n" + 
				"            overflow: auto;\r\n" + 
				"            float: left;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .Verticalitem {\r\n" + 
				"            display: block;\r\n" + 
				"            color: #000;\r\n" + 
				"            padding: 8px 16px;\r\n" + 
				"            text-decoration: none;\r\n" + 
				"            text-align: center;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .Verticalitem:hover:not(.active) {\r\n" + 
				"            background-color: #555;\r\n" + 
				"            color: white;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .dropdown-content a {\r\n" + 
				"            background-color: #f9f9f9;\r\n" + 
				"            color: black;\r\n" + 
				"            padding: 8px 16px;\r\n" + 
				"            text-decoration: none;\r\n" + 
				"            display: none;\r\n" + 
				"            text-align: center;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        a:hover,\r\n" + 
				"        .dropdown:hover .dropbtn {\r\n" + 
				"            background-color: #555;\r\n" + 
				"            color: white;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .dropdown-content a:hover {\r\n" + 
				"            background-color: #555;\r\n" + 
				"            color: white;\r\n" + 
				"            display: block;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .dropdown:hover .dropdown-content a {\r\n" + 
				"            display: block;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .row:after {\r\n" + 
				"            content: \"\";\r\n" + 
				"            display: table;\r\n" + 
				"            clear: both;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .content {\r\n" + 
				"            width: 75%;\r\n" + 
				"            background-color: lightblue;\r\n" + 
				"            padding: 6px;\r\n" + 
				"            margin-left: 170px;\r\n" + 
				"            margin-top: 5px;\r\n" + 
				"            //overflow: scroll;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .inpf {\r\n" + 
				"            height: 100%;\r\n" + 
				"            width: 75%;\r\n" + 
				"            background-color: lightblue;\r\n" + 
				"            padding: 6px;\r\n" + 
				"            margin-left: 170px;\r\n" + 
				"            margin-top: 5px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .card {\r\n" + 
				"            background-color: white;\r\n" + 
				"            padding: 7px;\r\n" + 
				"            margin-top: 20px;\r\n" + 
				"            height: 190px;\r\n" + 
				"            width: 90%-1;\r\n" + 
				"            overflow: hidden;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .cardtext {\r\n" + 
				"            background-color: lightblue;\r\n" + 
				"            overflow: hidden;\r\n" + 
				"            height: 90px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        img {\r\n" + 
				"            max-width: 150px;\r\n" + 
				"            float: left;\r\n" + 
				"            max-height: 170px;\r\n" + 
				"  			 width: auto;\r\n" + 
				"  			 height: auto;" +
				"        }\r\n" + 
				"        \r\n" + 
				"        .price {\r\n" + 
				"            background-color: lightgreen;\r\n" + 
				"            height: 20px;\r\n" + 
				"            width: 70px;\r\n" + 
				"            margin-top: 0px;\r\n" + 
				"            margin-right: 0%;\r\n" + 
				"            float: left;\r\n" + 
				"            overflow: auto;\r\n" + 
				"            text-align: right;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .seller {\r\n" + 
				"            background-color: lightgreen;\r\n" + 
				"            overflow: auto;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .sellbutton {\r\n" + 
				"            background-color: #4CAF50;\r\n" + 
				"            border: none;\r\n" + 
				"            color: white;\r\n" + 
				"            padding: 15px 32px;\r\n" + 
				"            text-align: center;\r\n" + 
				"            text-decoration: none;\r\n" + 
				"            display: inline-block;\r\n" + 
				"            font-size: 16px;\r\n" + 
				"            margin: 2px 2px;\r\n" + 
				"            cursor: pointer;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .form-body {\r\n" + 
				"            background: #fff;\r\n" + 
				"            padding: 20px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .login-form {\r\n" + 
				"            background: rgba(255, 255, 255, 0.8);\r\n" + 
				"            padding: 20px;\r\n" + 
				"            border-top: 3px solid#3e4043;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .innter-form {\r\n" + 
				"            padding-top: 20px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .final-login li {\r\n" + 
				"            width: 50%;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .nav-tabs {\r\n" + 
				"            border-bottom: none !important;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .nav-tabs>li {\r\n" + 
				"            color: #222 !important;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .nav-tabs>li.active>a,\r\n" + 
				"        .nav-tabs>li.active>a:hover,\r\n" + 
				"        .nav-tabs>li.active>a:focus {\r\n" + 
				"            color: #fff;\r\n" + 
				"            background-color: #d14d42;\r\n" + 
				"            border: none !important;\r\n" + 
				"            border-bottom-color: transparent;\r\n" + 
				"            border-radius: none !important;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .nav-tabs>li>a {\r\n" + 
				"            margin-right: 2px;\r\n" + 
				"            line-height: 1.428571429;\r\n" + 
				"            border: none !important;\r\n" + 
				"            border-radius: none !important;\r\n" + 
				"            text-transform: uppercase;\r\n" + 
				"            font-size: 16px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .social-login {\r\n" + 
				"            text-align: center;\r\n" + 
				"            font-size: 12px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .social-login p {\r\n" + 
				"            margin: 15px 0;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .social-login ul {\r\n" + 
				"            margin: 0;\r\n" + 
				"            padding: 0;\r\n" + 
				"            list-style-type: none;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .social-login ul li {\r\n" + 
				"            width: 33%;\r\n" + 
				"            float: left;\r\n" + 
				"            clear: fix;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .social-login ul li a {\r\n" + 
				"            font-size: 13px;\r\n" + 
				"            color: #fff;\r\n" + 
				"            text-decoration: none;\r\n" + 
				"            padding: 10px 0;\r\n" + 
				"            display: block;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .social-login ul li:nth-child(1) a {\r\n" + 
				"            background: #3b5998;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .social-login ul li:nth-child(2) a {\r\n" + 
				"            background: #e74c3d;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .social-login ul li:nth-child(3) a {\r\n" + 
				"            background: #3698d9;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .sa-innate-form input[type=text],\r\n" + 
				"        input[type=password],\r\n" + 
				"        input[type=file],\r\n" + 
				"        textarea,\r\n" + 
				"        select,\r\n" + 
				"        email {\r\n" + 
				"            font-size: 13px;\r\n" + 
				"            padding: 10px;\r\n" + 
				"            border: 1px solid#ccc;\r\n" + 
				"            outline: none;\r\n" + 
				"            width: 100%;\r\n" + 
				"            margin: 8px 0;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .sa-innate-form input[type=submit] {\r\n" + 
				"            border: 1px solid#e64b3b;\r\n" + 
				"            background: #e64b3b;\r\n" + 
				"            color: #fff;\r\n" + 
				"            padding: 10px 25px;\r\n" + 
				"            font-size: 14px;\r\n" + 
				"            margin-top: 5px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .sa-innate-form input[type=submit]:hover {\r\n" + 
				"            border: 1px solid#db3b2b;\r\n" + 
				"            background: #db3b2b;\r\n" + 
				"            color: #fff;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .sa-innate-form button {\r\n" + 
				"            border: 1px solid#e64b3b;\r\n" + 
				"            background: #e64b3b;\r\n" + 
				"            color: #fff;\r\n" + 
				"            padding: 10px 25px;\r\n" + 
				"            font-size: 14px;\r\n" + 
				"            margin-top: 5px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .sa-innate-form button:hover {\r\n" + 
				"            border: 1px solid#db3b2b;\r\n" + 
				"            background: #db3b2b;\r\n" + 
				"            color: #fff;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .sa-innate-form p {\r\n" + 
				"            font-size: 13px;\r\n" + 
				"            padding-top: 10px;\r\n" + 
				"        }\r\n" + 
				"    </style>\r\n" + 
				"</head>");
		//head結束
		//body開始
		out.println("<body>\r\n" + 
				"    <h1 class=\"title\">DringLow</h1>\r\n" + 
				"    <ul class=\"Horizontalbar\">\r\n" + 
				"        <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href='home.html'>Home</a></li>\r\n" + 
				"        <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href=\"course\">課程評價</a></li>\r\n" + 
				"        <li class=\"Horizontalborder\"><a class=\"active Horizontalitem\" href=\"book\">二手書</a></li>\r\n" + 
				"        <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href=\"stuff.html\">新舊物品</a></li>\r\n" + 
				"        <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href=\"gossip\">聊天</a></li>");
		String user="";
		if(request.getSession().getAttribute("login")!=null) {
			user=(String)request.getSession().getAttribute("login");
		}
		if(user=="") {	//沒登入(1個)
			out.println("<li style=\"float:right;border-left:1px solid #bbb\"><a class=\"Horizontalitem\">Sign in</a></li>\r\n");
		}
		else {	//以登入(2個)
			out.println("<li style=\"float:right;border-left:1px solid #bbb\"><a class=\"Horizontalitem\" href=\"logout.do\">Sign out</a></li>\r\n");
			out.println("<li style=\"float:right;border-left:1px solid #bbb\"><a class=\"Horizontalitem\">"+user+"</a></li>");
		}
		out.println("</ul>\r\n" + 
				"    <ul class=\"Verticalbar\">\r\n" + 
				"        <li><a class=\"Verticalitem\" href=\"book\">搜尋</a></li>\r\n" + 
				"        <li class=\"dropdown\">\r\n" + 
				"            <a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">電機資訊學院</a>\r\n" + 
				"            <div class=\"dropdown-content\">\r\n" + 
				"                <a href=\"book?tag=0\">資訊工程學系</a>\r\n" + 
				"                <a href=\"book?tag=1\">電機資訊學系</a>\r\n" + 
				"                <a href=\"book?tag=2\">通訊系</a>\r\n" + 
				"                <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"            </div>\r\n" + 
				"        </li>\r\n" + 
				"        <li class=\"dropdown\">\r\n" + 
				"            <a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">法律學院</a>\r\n" + 
				"            <div class=\"dropdown-content\">\r\n" + 
				"                <a href=\"book?tag=3\">法律系司法組</a>\r\n" + 
				"                <a href=\"book?tag=4\">法律系法學組</a>\r\n" + 
				"                <a href=\"book?tag=5\">法律系財經法組</a>\r\n" + 
				"                <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"            </div>\r\n" + 
				"        </li>\r\n" + 
				"        <li class=\"dropdown\">\r\n" + 
				"            <a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">商學院</a>\r\n" + 
				"            <div class=\"dropdown-content\">\r\n" + 
				"                <a href=\"book?tag=6\">企業管理學系</a>\r\n" + 
				"                <a href=\"book?tag=7\">金融與合作經營學系</a>\r\n" + 
				"                <a href=\"book?tag=8\">休閒運動管理學系</a>\r\n" + 
				"                <a href=\"book?tag=9\">會計學系</a>\r\n" + 
				"                <a href=\"book?tag=10\">統計系</a>\r\n" + 
				"                <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"            </div>\r\n" + 
				"        </li>\r\n" + 
				"        <li class=\"dropdown\">\r\n" + 
				"            <a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">公共事務學院</a>\r\n" + 
				"            <div class=\"dropdown-content\">\r\n" + 
				"                <a href=\"book?tag=11\">公共行政暨政策學系</a>\r\n" + 
				"                <a href=\"book?tag=12\">財政學系</a>\r\n" + 
				"                <a href=\"book?tag=13\">不動產與城鄉環境學系</a>\r\n" + 
				"                <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"            </div>\r\n" + 
				"        </li>\r\n" + 
				"        <li class=\"dropdown\">\r\n" + 
				"            <a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">人文學院</a>\r\n" + 
				"            <div class=\"dropdown-content\">\r\n" + 
				"                <a href=\"course?tag=14\">中國文學系</a>\r\n" + 
				"                <a href=\"course?tag=15\">應用外語學系</a>\r\n" + 
				"                <a href=\"course?tag=16\">歷史學系</a>\r\n" + 
				"                <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"            </div>\r\n" + 
				"        </li>\r\n" + 
				"        <li class=\"dropdown\">\r\n" + 
				"            <a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">社會科學院</a>\r\n" + 
				"            <div class=\"dropdown-content\">\r\n" + 
				"                <a href=\"book?tag=17\">社會學系</a>\r\n" + 
				"                <a href=\"book?tag=18\">社會工作學系</a>\r\n" + 
				"                <a href=\"book?tag=19\">經濟學系</a>\r\n" + 
				"                <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"            </div>\r\n" + 
				"        </li>\r\n" + 
				"    </ul>\r\n" + 
				"    <a  class=\"sellbutton\" id=\"sellbtn\" data-toggle=\"modal\" data-target=\"#myModal\" style=\"position:fixed; top:90%; left:90%;\">拍賣</a>\r\n" + 
				"    <a  class=\"sellbutton\" id=\"backbtn\" style=\"position:fixed; top:90%; left:90%;\">回前頁</a>");
		//彈出視窗開始
		out.println("<!-- Modal -->\r\n" + 
				"    <div class=\"modal fade\" id=\"myModal\" role=\"dialog\">\r\n" + 
				"        <div class=\"modal-dialog\">\r\n" + 
				"            <!-- Modal content-->\r\n" + 
				"            <div class=\"modal-content\">\r\n" + 
				"                <div class=\"modal-header\">\r\n" + 
				"                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n" + 
				"                    <h4 class=\"modal-title\" id=\"curtitle\">Modal Header</h4>\r\n" + 
				"                </div>\r\n" + 
				"                <div class=\"modal-body\">\r\n" + 
				"                    <form method=\"post\" action=\"salebook.do\" data-toggle=\"validator\" enctype='multipart/form-data' id=\"mySubForm\">\r\n" + 
				"					\r\n" + 
				"                        <div class=\"form-group\">\r\n" + 
				"                            <label for=\"department\">科系:</label>\r\n" + 
				"								<select id=\"department\" name=\"tag\"  class=\"form-control\" placeholder=\"科系\">\r\n" + 
				"								  <option value=\"0\">資訊工程學系</option>\r\n" + 
				"								  <option value=\"1\">電機資訊系</option>\r\n" + 
				"								  <option value=\"2\">通訊學系</option>\r\n" + 
				"								  <option value=\"3\">法律系司法組</option>\r\n" + 
				"								  <option value=\"4\">法律系法學組</option>\r\n" + 
				"								  <option value=\"5\">法律系財經法組</option>\r\n" + 
				"								  <option value=\"6\">企業管理學系</option>\r\n" + 
				"								  <option value=\"7\">金融與合作經營學系</option>\r\n" + 
				"								  <option value=\"8\">休閒運動管理學系</option>\r\n" + 
				"								  <option value=\"9\">會計系</option>\r\n" + 
				"								  <option value=\"10\">統計系</option>\r\n" + 
				"								  <option value=\"11\">公共事務暨行政學系</option>\r\n" + 
				"								  <option value=\"12\">財政學系</option>\r\n" + 
				"								  <option value=\"13\">不動產與城鄉環境學系</option>\r\n" + 
				"								  <option value=\"14\">中國文學學系</option>\r\n" + 
				"								  <option value=\"15\">應用外語學系</option>\r\n" + 
				"								  <option value=\"16\">歷史學系</option>\r\n" + 
				"								  <option value=\"17\">社會學系</option>\r\n" + 
				"								  <option value=\"18\">社會工作學系</option>\r\n" + 
				"								  <option value=\"19\">經濟學系</option>\r\n" + 
				"								</select>\r\n" + 
				"                        </div>\r\n" + 
				"                        <div class=\"form-group\">\r\n" + 
				"                            <label for=\"bookname\">書名:</label>\r\n" + 
				"                            <input type=\"text\" class=\"form-control\" id=\"coursename\" name=\"bookname\" placeholder=\"書名\" required>\r\n" +  
				"                                            <div class=\"help-block with-errors\"></div>" +
				"                        </div>\r\n" + 
				"                        <div class=\"form-group\">\r\n" + 
				"                            <label for=\"price\">價錢:</label>\r\n" + 
				"                            <input type=\"number\" min=\"0\" class=\"form-control\" id=\"price\" name=\"price\" placeholder=\"價錢\" required>\r\n" + 
				"                                            <div class=\"help-block with-errors\"></div>" + 
				"                        </div>\r\n" + 
				"                        <div class=\"form-group\">\r\n" + 
				"                            <h4>圖片1:</h4>\r\n" + 
				"                            <div class=\"input-group\">\r\n" + 
				"                                <label class=\"input-group-btn\">\r\n" + 
				"                                    <span class=\"btn btn-primary\">\r\n" + 
				"                           瀏覽檔案&hellip; <input type=\"file\" accept=\"image/*\" name=\"pic1\" value=\"\" style=\"display: none;\" required>\r\n" +
				"                           </span>\r\n" +  
				"                                </label>\r\n" +
				"                                <input type=\"text\" class=\"form-control\" readonly>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"\r\n" + 
				"                        <p id=\"passwordHelpBlock\" class=\"form-text text-muted\">\r\n" + 
				"                            請至少上傳一張圖片\r\n" + 
				"                        </p>" +
				"                        <div class=\"form-group\">\r\n" + 
				"                            <h4>圖片2:</h4>\r\n" + 
				"                            <div class=\"input-group\">\r\n" + 
				"                                <label class=\"input-group-btn\">\r\n" + 
				"                                    <span class=\"btn btn-primary\">\r\n" + 
				"                           瀏覽檔案&hellip; <input type=\"file\" accept=\"image/*\" name=\"pic2\" value=\"\" style=\"display: none;\" >\r\n" + 
				"                           </span>\r\n" + 
				"                                </label>\r\n" + 
				"                                <input type=\"text\" class=\"form-control\" readonly>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                        <div class=\"form-group\">\r\n" + 
				"                            <h4>圖片3:</h4>\r\n" + 
				"                            <div class=\"input-group\">\r\n" + 
				"                                <label class=\"input-group-btn\">\r\n" + 
				"                                    <span class=\"btn btn-primary\">\r\n" + 
				"                           瀏覽檔案&hellip; <input type=\"file\" accept=\"image/*\" name=\"pic3\" value=\"\" style=\"display: none;\">\r\n" + 
				"                           </span>\r\n" + 
				"                                </label>\r\n" + 
				"                                <input type=\"text\" class=\"form-control\" readonly>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                        <label for=\"comment\">描述:</label>\r\n" + 
				"                        <textarea class=\"form-control\" maxlength=\"120\" data-minlength=\"10\" name=\"describe\" rows=\"5\" id=\"comment\" required></textarea>\r\n" +
				"                        <br>\r\n" + 
				"                        <p id=\"passwordHelpBlock\" class=\"form-text text-muted\">\r\n" + 
				"                            請輸入10~120字(不包含空格)\r\n" + 
				"                        </p>" +
				"                        <input class=\"btn btn-info btn-lg  center-block\" style=\"width:100%\" type=\"submit\" value=\"Submit\">\r\n" + 
				"                    </form>\r\n" + 
				"                </div>\r\n" + 
				"                <div class=\"modal-footer\">\r\n" + 
				"                    <button type=\"button\" class=\"btn btn-default \" data-dismiss=\"modal\">Close</button>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"    </div>\r\n" + 
				"    <!-- Modal end -->");
		//彈出視窗結束
		//彈出視窗開始
		out.println("<!-- login Modal -->\r\n" + 
				"    <div class=\"modal fade\" id=\"loginModal\" role=\"dialog\">\r\n" + 
				"        <div class=\"modal-dialog\">\r\n" + 
				"            <!-- Modal content-->\r\n" + 
				"            <div class=\"modal-content\">\r\n" + 
				"                <div class=\"modal-header\">\r\n" + 
				"                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n" + 
				"                    <h4 class=\"modal-title\" id=\"curtitle\">登入</h4>\r\n" + 
				"                </div>\r\n" + 
				"                <div class=\"modal-body\">\r\n" + 
				"                    <div class=\"form-body\">\r\n" + 
				"                        <ul class=\"nav nav-tabs final-login\">\r\n" + 
				"                            <li class=\"active\"><a data-toggle=\"tab\" href=\"#sectionA\">登入</a></li>\r\n" + 
				"                            <li><a data-toggle=\"tab\" href=\"#sectionB\">註冊</a></li>\r\n" + 
				"                        </ul>\r\n" + 
				"                        <div class=\"tab-content\">\r\n" + 
				"                            <div id=\"sectionA\" class=\"tab-pane fade in active\">\r\n" + 
				"                                <div class=\"innter-form\">\r\n" + 
				"                                    <form class=\"sa-innate-form\" action='../login.do' method=\"post\" id=\"loginform\" data-toggle=\"validator\" role=\"form\">\r\n" + 
				"                                        <div class=\"form-group\">\r\n" + 
				"                                            <label for=\"inputEmail\" class=\"control-label\">Email</label>\r\n" + 
				"                                            <input type=\"email\" class=\"form-control\" id=\"inputEmail\" placeholder=\"Email\" required>\r\n" + 
				"                                            <div class=\"help-block with-errors\"></div>\r\n" + 
				"                                        </div>\r\n" + 
				"                                        <div class=\"form-group\">\r\n" + 
				"                                            <label for=\"inputPassword\" class=\"control-label\">密碼</label>\r\n" + 
				"                                            <div class=\"form-group\">\r\n" + 
				"                                                <input type=\"password\" class=\"form-control\" data-minlength=\"6\" maxlength=\"16\" id=\"inputPassword\" placeholder=\"Password\" required>\r\n" + 
				"                                                <div class=\"help-block\"></div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                        </div>\r\n" + 
				"                                        <button type=\"submit\">登入</button>\r\n" + 
				"                                    </form>\r\n" + 
				"                                </div>\r\n" + 
				"                                <div class=\"clearfix\"></div>\r\n" + 
				"                            </div>\r\n" + 
				"                            <div id=\"sectionB\" class=\"tab-pane fade\">\r\n" + 
				"                                <div class=\"innter-form\">\r\n" + 
				"                                    <form class=\"sa-innate-form\" method=\"post\" action='../register.do' id=\"signinform\" data-toggle=\"validator\" role=\"form\">\r\n" + 
				"                                        <div class=\"form-group\">\r\n" + 
				"                                            <label for=\"inputEmail2\" class=\"control-label\">Email</label>\r\n" + 
				"                                            <input type=\"email\" class=\"form-control\" id=\"inputEmail2\" placeholder=\"Email\" required>\r\n" + 
				"                                            <div class=\"help-block with-errors\"></div>\r\n" + 
				"                                        </div>\r\n" + 
				"                                        <div class=\"form-group\">\r\n" + 
				"                                            <label for=\"inputPassword2\" class=\"control-label\">密碼</label>\r\n" + 
				"                                            <div class=\"form-group\">\r\n" + 
				"                                                <input type=\"password\" class=\"form-control\" data-minlength=\"6\" maxlength=\"16\" id=\"inputPassword2\" placeholder=\"Password\" required>\r\n" + 
				"                                                <div class=\"help-block\">6~16字</div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                            <label for=\"inputPasswordConfirm\" class=\"control-label\">請再次輸入密碼</label>\r\n" + 
				"                                            <div class=\"form-group\">\r\n" + 
				"                                                <input type=\"password\" class=\"form-control\" id=\"inputPasswordConfirm\" maxlength=\"16\" data-match=\"#inputPassword2\" data-match-error=\"兩次輸入密碼不同\" placeholder=\"Confirm\" required>\r\n" + 
				"                                                <div class=\"help-block with-errors\"></div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                        </div>\r\n" + 
				"                                        <button type=\"submit\">註冊</button>\r\n" + 
				"                                    </form>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                    </div>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"    </div>\r\n" + 
				"    <!-- login Modal end -->\r\n");
		//彈出視窗結束
		out.println("<div class=\"row\">\r\n" + 
				"        <div class=\"content\">\r\n");
		for(List<String> l:list) {
			out.println("<div class=\"card btn\" style=\"text-align:left; padding-left:6px ;width:100%\" id='"+l.get(0)+"'>");
			out.println("<img src='"+LOCATION+l.get(0)+"_1.jpg'/>");
			out.println("<h2 style=\"margin-top:0px;\">"+l.get(1)+"</h2>");
			out.println("<ul style=\"list-style-type: none;\">\r\n" + 
					"            <li>\r\n" + 
					"                <h1 style=\"display: inline-block ; margin-top:2px\">"+l.get(3)+"</h1><span class=\"label label-default\">賣家</span>\r\n" + 
					"                <h1 style=\"color: red; margin-top:2px\">$"+l.get(2)+"</h1>\r\n" + 
					"            </li>\r\n" + 
					"        </ul>\r\n" + 
					"    </div>");
		}
		out.println("</div>\r\n" + 
				"    </div>\r\n" + 
				"\r\n" + 
				"    <script>\r\n" + 
				"        $(document).ready(function() {\r\n" + 
				"            $(\"a.Horizontalitem\").click(function(event) {\r\n" + 
				"                var item = this;\r\n" + 
				"                if (item.textContent == 'Sign in')\r\n" + 
				"                    $(\"#loginModal\").modal();\r\n" + 
				"            });\r\n" + 
				"\r\n" + 
				"        });\r\n" + 
				"    </script>\r\n" + 
				"    <script>\r\n" + 
				"        var inPageFrame;\r\n" + 
				"        var content;\r\n" + 
				"        var sbtn;\r\n" + 
				"        var bbtn;\r\n" + 
				"        (function() {\r\n" + 
				"            content = $(\".content\")[0];\r\n" + 
				"            sbtn = $('#sellbtn')[0];\r\n" + 
				"            bbtn = $('#backbtn')[0];\r\n" + 
				"            inPageFrame = document.createElement('iframe');\r\n" + 
				"            inPageFrame.className = 'inpf';\r\n" + 
				"            inPageFrame.style.border = 'none';\r\n" + 
				"            inPageFrame.style.display = 'none';\r\n" + 
				"            inPageFrame.style.border = '2px solid grey';\r\n" + 
				"            bbtn.style.display = 'none';\r\n" + 
				"            $(\".row\")[0].appendChild(inPageFrame);\r\n" + 
				"        })();\r\n" + 
				"\r\n" + 
				"        function getTag() {\r\n" + 
				"            var url = new URL(window.location.href);\r\n" + 
				"            return c = url.searchParams.get(\"tag\");\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        function gotoID(id) {\r\n" + 
				"            var s = window.location.href.split('book');\r\n" + 
				"            var url = new URL(s[0] + \"book/in\");\r\n" + 
				"            url.searchParams.set(\"id\", id);\r\n" + 
				"            inPageFrame.src = url;\r\n" + 
				"            //inPageFrame.src = \"\\二手書買賣-點進去的彈跳視窗.html\";\r\n" + 
				"            content.style.display = \"none\";\r\n" + 
				"            sbtn.style.display = \"none\";\r\n" + 
				"            inPageFrame.style.display = 'block';\r\n" + 
				"            bbtn.style.display = 'block';\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        $(function() {\r\n" + 
				"            $('#backbtn').click(function(event) {\r\n" + 
				"                content.style.display = \"block\";\r\n" + 
				"                sbtn.style.display = \"block\";\r\n" + 
				"                inPageFrame.style.display = 'none';\r\n" + 
				"                bbtn.style.display = 'none';\r\n" + 
				"            });\r\n" + 
				"            $('#sellbtn').click(function(event) {\r\n" + 
				"                $('#curtitle')[0].textContent = getTag();\r\n" + 
				"            });\r\n" + 
				"            $(document).on('change', ':file', function() {\r\n" + 
				"                var input = $(this),\r\n" + 
				"                    numFiles = input.get(0).files ? input.get(0).files.length : 1,\r\n" + 
				"                    label = input.val().replace(/\\\\/g, '/').replace(/.*\\//, '');\r\n" + 
				"                input.trigger('fileselect', [numFiles, label]);\r\n" + 
				"            });\r\n" + 
				"\r\n" + 
				"            $(document).ready(function() {\r\n" + 
				"                $(':file').on('fileselect', function(event, numFiles, label) {\r\n" + 
				"\r\n" + 
				"                    var input = $(this).parents('.input-group').find(':text'),\r\n" + 
				"                        log = numFiles > 1 ? numFiles + ' files selected' : label;\r\n" + 
				"\r\n" + 
				"                    if (input.length) {\r\n" + 
				"                        input.val(log);\r\n" + 
				"                    } else {\r\n" + 
				"                        if (log) alert(log);\r\n" + 
				"                    }\r\n" + 
				"\r\n" + 
				"                });\r\n" + 
				"            });\r\n" + 
				"\r\n" + 
				"            $(\".card\").click(function() {\r\n" + 
				"                gotoID(this.id);\r\n" + 
				"            });\r\n" + 
				"\r\n" + 
				"        });\r\n" + 
				"    </script>\r\n" + 
				"</body>\r\n" + 
				"\r\n" + 
				"</html>\r\n");
		
        
        
		out.close();
	}
		/*out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		out.println("<html>");
        out.println("<head>");
        out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
        out.println("<title>book</title>");
        out.println("</head>");
        out.println("<body>");
		for(List<String> l:list) {
			out.println("<a href='book/in?id="+l.get(0)+"'>go </a>");
			out.println(l.get(1)+" "+l.get(2)+" "+l.get(3)+"<br>");
			
			out.println("<br>");
		}
		out.println("</body>");*/
}
