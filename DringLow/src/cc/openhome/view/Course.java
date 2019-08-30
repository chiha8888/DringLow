package cc.openhome.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.sun.org.glassfish.external.statistics.AverageRangeStatistic;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import cc.openhome.model.Connector;
import cc.openhome.model.DBInterface;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class Course
 */
@WebServlet(urlPatterns= {"/course"}
			)
public class Course extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<List<String>> list;
	private DecimalFormat df;
	private PrintWriter out;
	private String displaynone;
	
	@Override
	public void init() throws ServletException{
		df=new DecimalFormat("#.#");
		df.applyPattern("0.0");
		displaynone = "style='display:none'"; 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (SQLException e) {
			out.close();
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (SQLException e) {
			out.close();
			e.printStackTrace();
		}
	}
	
	private void processRequest(HttpServletRequest request,HttpServletResponse response)throws IOException,SQLException{
		
		DBInterface dbInterface=(DBInterface)getServletContext().getAttribute("dbInterface");
		String id=request.getParameter("tag");
		if(id.isEmpty()||id==null) {
			id="999";
		}
		List<List<String>> list=dbInterface.getCourses(Integer.parseInt(id));
		//{id,course name,teacher name,score,score,score,people_num}
		//dbInterface.Close();
		
		response.setContentType("text/html;charset=UTF-8");
		out=response.getWriter();
		//開始 head
		out.println("<html>\r\n" + 
				"<head>\r\n" + 
				" 	   <title>課程 - DringLow</title>"+	
				"      <meta charset=\"utf-8\">\r\n" + 
				"      <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" charset=\"utf-8\">\r\n" + 
				"      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\" charset=\"utf-8\"></script>\r\n" + 
				"      <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" charset=\"utf-8\"></script>\r\n" + 
				"	   <script src=\"https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js\" charset=\"utf-8\"></script>"+
				"<style>\r\n" + 
				"\r\n" + 
				".title {\r\n" + 
				"	margin: 0;\r\n" + 
				"    background: lightgrey;\r\n" + 
				"   // color: red;\r\n" + 
				"    border: 0;  \r\n" + 
				"    //text-align: center;\r\n" + 
				"}\r\n" + 
				"li:last-child {\r\n" + 
				"    border-right: none;\r\n" + 
				"}\r\n" + 
				".Horizontalitem {\r\n" + 
				"	display: block;\r\n" + 
				"    color: white;\r\n" + 
				"    text-align: center;\r\n" + 
				"    padding: 10px 15px;\r\n" + 
				"    text-decoration: none;\r\n" + 
				"    \r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".Horizontalitem:hover:not(.active) {\r\n" + 
				"	background-color: green;\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				".active.Horizontalitem {\r\n" + 
				"	background-color: #4CAF50;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".Horizontalbar{\r\n" + 
				"	list-style-type: none;\r\n" + 
				"    margin: 0;\r\n" + 
				"    padding: 0;\r\n" + 
				"    background-color: black;\r\n" + 
				"    \r\n" + 
				"    overflow: hidden;\r\n" + 
				"}\r\n" + 
				".Horizontalborder {\r\n" + 
				"	float: left;\r\n" + 
				"    border-right:1px solid #bbb;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				".Verticalbar {\r\n" + 
				"	list-style-type: none;\r\n" + 
				"	background-color: #f1f1f1;\r\n" + 
				"    width: 150px;\r\n" + 
				"    margin-top: 20px;\r\n" + 
				"    padding: 0;\r\n" + 
				"    position: fixed;\r\n" + 
				"    overflow: auto;\r\n" + 
				"    float: left;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".Verticalitem {\r\n" + 
				"	display: block;\r\n" + 
				"    color: #000;\r\n" + 
				"    padding: 8px 16px;\r\n" + 
				"    text-decoration: none;\r\n" + 
				"    text-align: center;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".Verticalitem:hover:not(.active) {\r\n" + 
				"	background-color: #555;\r\n" + 
				"    color: white;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".content {\r\n" + 
				"	//height: 600px;\r\n" + 
				"	width:75%;\r\n" + 
				"    background-color: lightblue;\r\n" + 
				"	padding: 6px;    \r\n" + 
				"	margin-left: 170px;\r\n" + 
				"	margin-top: 5px;\r\n" + 
				"    //overflow: auto;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"		 .inpf {\r\n" + 
				"			height:100%;\r\n" + 
				"			 width:75%;\r\n" + 
				"			 background-color: lightblue;\r\n" + 
				"			 padding: 6px;    \r\n" + 
				"			 margin-left: 170px;\r\n" + 
				"			 margin-top: 5px;\r\n" + 
				"		 }\r\n" + 
				".card {\r\n" + 
				"     background-color: white;\r\n" + 
				"     padding: 7px;\r\n" + 
				"     margin-top: 20px;\r\n" + 
				"     height: 100px;\r\n" + 
				"     overflow: hidden;\r\n" + 
				"}\r\n" + 
				".dropdown-content a {\r\n" + 
				" 	background-color: #f9f9f9; \r\n" + 
				"    color: black;\r\n" + 
				"    padding: 8px 16px;\r\n" + 
				"    text-decoration: none;\r\n" + 
				"    display: none;\r\n" + 
				"    text-align: center;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"a:hover, .dropdown:hover .dropbtn {\r\n" + 
				"    background-color: #555;\r\n" + 
				"    color: white;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				".dropdown-content a:hover {\r\n" + 
				"background-color: #555 ;\r\n" + 
				"color: white;\r\n" + 
				"display: block;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				".dropdown:hover .dropdown-content a{\r\n" + 
				"    display: block;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".row:after {\r\n" + 
				"    content: \"\";\r\n" + 
				"    display: table;\r\n" + 
				"    clear: both;\r\n" + 
				"}\r\n" + 
				".sellbutton {\r\n" + 
				"    background-color: #4CAF50;\r\n" + 
				"    border: none;\r\n" + 
				"    color: white;\r\n" + 
				"    padding: 15px 32px;\r\n" + 
				"    text-align: center;\r\n" + 
				"    text-decoration: none;\r\n" + 
				"    display: inline-block;\r\n" + 
				"    font-size: 16px;\r\n" + 
				"    margin: 4px 2px;\r\n" + 
				"    cursor: pointer;\r\n" + 
				"   }\r\n" + 
				"   h1, h2 {\r\n" + 
				"    display: inline-block;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".form-body{\r\n" + 
				"    background:#fff;\r\n" + 
				"    padding:20px;\r\n" + 
				"}\r\n" + 
				".login-form{\r\n" + 
				"    background:rgba(255,255,255,0.8);\r\n" + 
				"	padding:20px;\r\n" + 
				"	border-top:3px solid#3e4043;\r\n" + 
				"}\r\n" + 
				".innter-form{\r\n" + 
				"	padding-top:20px;\r\n" + 
				"}\r\n" + 
				".final-login li{\r\n" + 
				"	width:50%;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".nav-tabs {\r\n" + 
				"    border-bottom: none !important;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".nav-tabs>li{\r\n" + 
				"	color:#222 !important;\r\n" + 
				"}\r\n" + 
				".nav-tabs>li.active>a, .nav-tabs>li.active>a:hover, .nav-tabs>li.active>a:focus {\r\n" + 
				"    color: #fff;\r\n" + 
				"    background-color: #d14d42;\r\n" + 
				"    border: none !important;\r\n" + 
				"    border-bottom-color: transparent;\r\n" + 
				"	border-radius:none !important;\r\n" + 
				"}\r\n" + 
				".nav-tabs>li>a {\r\n" + 
				"    margin-right: 2px;\r\n" + 
				"    line-height: 1.428571429;\r\n" + 
				"    border: none !important;\r\n" + 
				"    border-radius:none !important;\r\n" + 
				"	text-transform:uppercase;\r\n" + 
				"	font-size:16px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".social-login{\r\n" + 
				"	text-align:center;\r\n" + 
				"	font-size:12px;\r\n" + 
				"}\r\n" + 
				".social-login p{\r\n" + 
				"	margin:15px 0;\r\n" + 
				"}\r\n" + 
				".social-login ul{\r\n" + 
				"	margin:0;\r\n" + 
				"	padding:0;\r\n" + 
				"	list-style-type:none;\r\n" + 
				"}\r\n" + 
				".social-login ul li{\r\n" + 
				"	width:33%;\r\n" + 
				"	float:left;\r\n" + 
				"    clear:fix;\r\n" + 
				"}\r\n" + 
				".social-login ul li a{\r\n" + 
				"	font-size:13px;\r\n" + 
				"	color:#fff;\r\n" + 
				"	text-decoration:none;\r\n" + 
				"	padding:10px 0;\r\n" + 
				"	display:block;\r\n" + 
				"}\r\n" + 
				".social-login ul li:nth-child(1) a{\r\n" + 
				"	background:#3b5998;\r\n" + 
				"}\r\n" + 
				".social-login ul li:nth-child(2) a{\r\n" + 
				"	background:#e74c3d;\r\n" + 
				"}\r\n" + 
				".social-login ul li:nth-child(3) a{\r\n" + 
				"	background:#3698d9;\r\n" + 
				"}\r\n" + 
				".sa-innate-form input[type=text], input[type=password], input[type=file], textarea, select, email{\r\n" + 
				"    font-size:13px;\r\n" + 
				"	padding:10px;\r\n" + 
				"	border:1px solid#ccc;\r\n" + 
				"	outline:none;\r\n" + 
				"	width:100%;\r\n" + 
				"	margin:8px 0;\r\n" + 
				"	\r\n" + 
				"}\r\n" + 
				".sa-innate-form input[type=submit]{\r\n" + 
				"    border:1px solid#e64b3b;\r\n" + 
				"	background:#e64b3b;\r\n" + 
				"	color:#fff;\r\n" + 
				"	padding:10px 25px;\r\n" + 
				"	font-size:14px;\r\n" + 
				"	margin-top:5px;\r\n" + 
				"	}\r\n" + 
				"	.sa-innate-form input[type=submit]:hover{\r\n" + 
				"	border:1px solid#db3b2b;\r\n" + 
				"	background:#db3b2b;\r\n" + 
				"	color:#fff;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	.sa-innate-form button{\r\n" + 
				"	border:1px solid#e64b3b;\r\n" + 
				"	background:#e64b3b;\r\n" + 
				"	color:#fff;\r\n" + 
				"	padding:10px 25px;\r\n" + 
				"	font-size:14px;\r\n" + 
				"	margin-top:5px;\r\n" + 
				"	}\r\n" + 
				"	.sa-innate-form button:hover{\r\n" + 
				"	border:1px solid#db3b2b;\r\n" + 
				"	background:#db3b2b;\r\n" + 
				"	color:#fff;\r\n" + 
				"	}\r\n" + 
				"    .sa-innate-form p{\r\n" + 
				"        font-size:13px;\r\n" + 
				"        padding-top:10px;\r\n" + 
				"    }\r\n"+
				"</style>\r\n" + 
				"</head>");
		//head結束
		//body開始
		//是否已登入?
		String user="";
		if(request.getSession().getAttribute("login")!=null) {
			user=(String)request.getSession().getAttribute("login");
		}
		out.println("<body>\r\n" + 
				"<h1 class=\"title\">DringLow</h1>\r\n" + 
				"<ul class=\"Horizontalbar\">\r\n" + 
				"	 <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href='home.html'>Home</a></li>\r\n" + 
				" 	 <li class=\"Horizontalborder\"><a class=\"active Horizontalitem\" href=\"course\">課程評價</a></li>\r\n" + 
				" 	 <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href=\"book\">二手書</a></li>\r\n" + 
				"     <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href=\"stuff.html\">新舊物品</a></li>\r\n" + 
				"     <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href=\"gossip\">聊天</a></li>\r\n");
		if(user=="") {	//沒登入(1個)
			out.println("<li style=\"float:right;border-left:1px solid #bbb\"><a class=\"Horizontalitem\">Sign in</a></li>\r\n");
		}
		else {	//以登入(2個)
			out.println("<li style=\"float:right;border-left:1px solid #bbb\"><a class=\"Horizontalitem\" href=\"logout.do\">Sign out</a></li>\r\n");
			out.println("<li style=\"float:right;border-left:1px solid #bbb\"><a class=\"Horizontalitem\">"+user+"</a></li>");
		}
		
		out.println("</ul>\r\n" + 
				"\r\n" + 
				"<ul class=\"Verticalbar\">\r\n" + 
				"<li ><a class=\"Verticalitem\" href=\"course?tag=999\">全部</a></li>\r\n" + 
				"<li class=\"dropdown\"><a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">電機資訊學院</a>\r\n" + 
				" <div class=\"dropdown-content\">\r\n" + 
				"      <a href=\"course?tag=0\">資訊工程學系</a>\r\n" + 
				"      <a href=\"course?tag=1\">電機資訊學系</a>\r\n" + 
				"      <a href=\"course?tag=2\">通訊系</a>\r\n" + 
				"      <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"    </div></li>\r\n" + 
				"<li class=\"dropdown\"><a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">法律學院</a>\r\n" + 
				" <div class=\"dropdown-content\">\r\n" + 
				"      <a href=\"course?tag=3\">法律系司法組</a>\r\n" + 
				"      <a href=\"course?tag=4\">法律系法學組</a>\r\n" + 
				"      <a href=\"course?tag=5\">法律系財經法組</a>\r\n" + 
				"      <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"    </div></li>\r\n" + 
				"<li class=\"dropdown\"><a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">商學院</a>\r\n" + 
				" <div class=\"dropdown-content\">\r\n" + 
				"      <a href=\"course?tag=6\">企業管理學系</a>\r\n" + 
				"      <a href=\"course?tag=7\">金融與合作經營學系</a>\r\n" + 
				"      <a href=\"course?tag=8\">休閒運動管理學系</a>\r\n" + 
				"      <a href=\"course?tag=9\">會計學系</a>\r\n" + 
				"      <a href=\"course?tag=10\">統計系</a>\r\n" + 
				"      <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"    </div></li>   \r\n" + 
				"<li class=\"dropdown\"><a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">公共事務學院</a>\r\n" + 
				" <div class=\"dropdown-content\">\r\n" + 
				"      <a href=\"course?tag=11\">公共行政暨政策學系</a>\r\n" + 
				"      <a href=\"course?tag=12\">財政學系</a>\r\n" + 
				"      <a href=\"course?tag=13\">不動產與城鄉環境學系</a>\r\n" + 
				"      <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"    </div></li>   \r\n" + 
				"\r\n" + 
				"<li class=\"dropdown\"><a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">人文學院</a>\r\n" + 
				" <div class=\"dropdown-content\">\r\n" + 
				"      <a href=\"course?tag=14\">中國文學系</a>\r\n" + 
				"      <a href=\"course?tag=15\">應用外語學系</a>\r\n" + 
				"      <a href=\"course?tag=16\">歷史學系</a>\r\n" + 
				"      <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"    </div></li>   \r\n" + 
				"<li class=\"dropdown\"><a class=\"Verticalitem dropbtn\" href=\"javascript:void(0)\">社會科學院</a>\r\n" + 
				" <div class=\"dropdown-content\">\r\n" + 
				"      <a href=\"course?tag=17\">社會學系</a>\r\n" + 
				"      <a href=\"course?tag=18\">社會工作學系</a>\r\n" + 
				"      <a href=\"course?tag=19\">經濟學系</a>\r\n" + 
				"      <a style=\"background-color:#f9f9f9\"> </a>\r\n" + 
				"    </div></li>   \r\n" + 
				"</ul >");
		//
		out.println("<div class=\"row\">\r\n" + 
				"  <div class=\"content\" >");
		out.println("<!-- login Modal -->\r\n" + 
				"      <div class=\"modal fade\" id=\"loginModal\" role=\"dialog\">\r\n" + 
				"         <div class=\"modal-dialog\">\r\n" + 
				"            <!-- Modal content-->\r\n" + 
				"            <div class=\"modal-content\">\r\n" + 
				"               <div class=\"modal-header\">\r\n" + 
				"                  <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n" + 
				"                  <h4 class=\"modal-title\" id=\"curtitle\">登入</h4>\r\n" + 
				"               </div>\r\n" + 
				"               <div class=\"modal-body\">\r\n" + 
				"			<div class=\"form-body\">\r\n" + 
				"    <ul class=\"nav nav-tabs final-login\">\r\n" + 
				"        <li class=\"active\"><a data-toggle=\"tab\" href=\"#sectionA\">登入</a></li>\r\n" + 
				"        <li><a data-toggle=\"tab\" href=\"#sectionB\">註冊</a></li>\r\n" + 
				"    </ul>\r\n" + 
				"    <div class=\"tab-content\">\r\n" + 
				"        <div id=\"sectionA\" class=\"tab-pane fade in active\">\r\n" + 
				"        <div class=\"innter-form\">\r\n" + 
				"            <form class=\"sa-innate-form\" method=\"post\" action='login.do' id=\"loginform\" data-toggle=\"validator\" role=\"form\">\r\n" + 
				"			<div class=\"form-group\">\r\n" + 
				"				<label for=\"inputEmail\" class=\"control-label\">Email</label>\r\n" + 
				"				<input type=\"email\" name='email' class=\"form-control\" id=\"inputEmail\" placeholder=\"Email\" required>\r\n" + 
				"				<div class=\"help-block with-errors\"></div>\r\n" + 
				"			 </div>\r\n" + 
				"			 <div class=\"form-group\">\r\n" + 
				"			<label for=\"inputPassword\" class=\"control-label\">密碼</label>\r\n" + 
				"			  <div class=\"form-group\">\r\n" + 
				"				<input type=\"password\" name='password' class=\"form-control\" data-minlength=\"6\"  maxlength=\"16\" id=\"inputPassword\" placeholder=\"Password\" required>\r\n" + 
				"				<div class=\"help-block\"></div>\r\n" + 
				"			  </div>\r\n" + 
				"			</div>\r\n" + 
				"            <button type=\"submit\">登入</button>\r\n" + 
				"            </form>\r\n" + 
				"            </div>\r\n" + 
				"            <div class=\"clearfix\"></div>\r\n" + 
				"        </div>\r\n" + 
				"        <div id=\"sectionB\" class=\"tab-pane fade\">\r\n" + 
				"        <div class=\"innter-form\">\r\n" + 
				"            <form class=\"sa-innate-form\" method=\"post\" action='register.do' id=\"signinform\" data-toggle=\"validator\" role=\"form\">\r\n" + 
				"			<div class=\"form-group\">\r\n" + 
				"				<label for=\"inputEmail2\" class=\"control-label\">Email</label>\r\n" + 
				"				<input type=\"email\" name='email' class=\"form-control\" id=\"inputEmail2\" placeholder=\"Email\" required>\r\n" + 
				"				<div class=\"help-block with-errors\"></div>\r\n" + 
				"			 </div>\r\n" + 
				"			 <div class=\"form-group\">\r\n" + 
				"			<label for=\"inputPassword2\" class=\"control-label\">密碼</label>\r\n" + 
				"			  <div class=\"form-group\">\r\n" + 
				"				<input type=\"password\" name='password' class=\"form-control\" data-minlength=\"6\"  maxlength=\"16\" id=\"inputPassword2\" placeholder=\"Password\" required>\r\n" + 
				"				<div class=\"help-block\">6~16字</div>\r\n" + 
				"			  </div>\r\n" + 
				"			<label for=\"inputPasswordConfirm\" class=\"control-label\">請再次輸入密碼</label>\r\n" + 
				"			  <div class=\"form-group\">\r\n" + 
				"				<input type=\"password\" name='confirmedpassword' class=\"form-control\" id=\"inputPasswordConfirm\"  maxlength=\"16\" data-match=\"#inputPassword2\" data-match-error=\"兩次輸入密碼不同\" placeholder=\"Confirm\" required>\r\n" + 
				"				<div class=\"help-block with-errors\"></div>\r\n" + 
				"			  </div>\r\n" + 
				"			</div>\r\n" + 
				"            <button type=\"submit\">註冊</button>\r\n" + 
				"            </form>\r\n" + 
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"    </div>\r\n" + 
				"    </div>\r\n" + 
				"               </div>\r\n" + 
				"            </div>\r\n" + 
				"         </div>\r\n" + 
				"      </div>\r\n" + 
				"	        <!-- login Modal end -->");
		
		for(List<String> l:list) {
			out.println("<div class=\"card\" id = \""+l.get(0)+"\">");
			out.println("\r\n" + 
					"	<h1>"+ l.get(1)+"<span class=\"label label-default\">"+l.get(2)+"</span></h1> ");
			out.println("<button type=\"button\" class=\"btn btn-danger pull-right\" style=\"top:30%;position:relative;\">老師 <span class=\"badge\">"+calAverage(l.get(3), l.get(6))+"</span></button>");
			out.println("<button type=\"button\" class=\"btn btn-success pull-right\" style=\"top:30%;position:relative;\">豐富度 <span class=\"badge\">"+calAverage(l.get(4),l.get(6))+"</span></button> ");
			out.println("<button type=\"button\" class=\"btn btn-primary pull-right\" style=\"top:30%;position:relative;\" >甜度 <span class=\"badge\">"+calAverage(l.get(5), l.get(6))+"</span></button>");
			out.println("</div>");
		}
		out.println("    \r\n" + 
				"</div>\r\n" + 
				"</div>");
		//
		out.println("<script>\r\n" + 
				"	  \r\n" + 
				"         $(function() {\r\n" + 
				"           $(\".card\").click(function() { \r\n" + 
				"				gotoID(this.id);\r\n" + 
				"           });\r\n" + 
				"         });\r\n" + 
				"         function gotoID(id){\r\n" + 
				"			var s=window.location.href.split('course');\r\n"+
				"			var url = new URL(s[0]+\"course/in\");\r\n"	+
				"         	url.searchParams.set(\"id\",id);\r\n" + 
				"			window.location.href = url.href;\r\n" + 
				"			//window.location.href = \"課程評價2 (1).html\";\r\n" + 
				"         }\r\n" + 
				"		 \r\n" + 
				"           $(\".card\").click(function() { \r\n" + 
				"\r\n" + 
				"				gotoID(this.id);\r\n" + 
				"           });\r\n" + 
				"</script>\r\n" +
				"<script>\r\n" + 
				"		$(document).ready(function(){\r\n" + 
				"			$(\"a.Horizontalitem\").click(function(event){\r\n" + 
				"				var item = this;\r\n" + 
				"				if(item.textContent=='Sign in')\r\n" + 
				"					$(\"#loginModal\").modal();\r\n" + 
				"			});\r\n" + 
				"			\r\n" + 
				"		});\r\n" + 
				"	  </script>\r\n"+
				"\r\n" + 
				"</body>\r\n" + 
				"</html>");
	
		out.close();
	}
	
	private String calAverage(String a,String n) {
		if(Integer.parseInt(n)==0) {
			return "#";
		}
		double ave=Double.parseDouble(a)/Double.parseDouble(n);
		return df.format(ave);
	}
	
		/*out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		out.println("<html>");
		out.println("<head>");
        out.println("<meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
		out.println("<title>課程 - DringLow</title>");
        out.println("</head>");
		out.println("<body>");
		for(List<String> l:list) {
			out.println("<a href='course/in?id="+l.get(0)+"'>"+l.get(1)+"</a>");
			String average="尚無評價";
			if(Float.parseFloat(l.get(6))!=0) {
				average=calAverage(l.get(3), l.get(4), l.get(5), l.get(6));
			}

			out.println(" "+l.get(2)+" "+"綜合評價: "+average+"<br>");
		}
		out.println("</body>");
		out.println("</html>");
		out.close();*/
}
