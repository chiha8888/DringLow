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
import sun.text.normalizer.UTF16;

/**
 * Servlet implementation class Gossip
 */
@WebServlet("/gossip")
public class Gossip extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private List<List<String>> list;
	private List<List<String>> down;
	private List<String> up;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			out.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			out.close();
		}
	}

	private void processRequest(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		DBInterface dbInterface = (DBInterface)getServletContext().getAttribute("dbInterface");
		list=dbInterface.getGossips();
		//{id，留言數，發文者，標題},{id，留言數，發文者，標題},{id，留言數，發文者，標題}......
		
		
		response.setContentType("text/html;charset=UTF-8");
		out=response.getWriter();
		
		//head
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"\r\n" + 
				"<head>\r\n" + 
				"    <title>聊天 - DringLow</title>\r\n" + 
				"    <meta charset=\"utf-8\">\r\n" + 
				"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" charset=\"utf-8\">\r\n" + 
				"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\" charset=\"utf-8\"></script>\r\n" + 
				"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" charset=\"utf-8\"></script>\r\n" + 
				"\r\n" + 
				"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js\" charset=\"utf-8\"></script>\r\n" + 
				"    <style>\r\n" + 
				"        .title {\r\n" + 
				"            margin: 0;\r\n" + 
				"            background: lightgrey;\r\n" + 
				"            // color: red;\r\n" + 
				"            border: 0;\r\n" + 
				"            //text-align: center;\r\n" + 
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
				"        #login {\r\n" + 
				"            display: none;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .login,\r\n" + 
				"        .logout {\r\n" + 
				"            position: absolute;\r\n" + 
				"            top: -3px;\r\n" + 
				"            right: 0;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .page-header {\r\n" + 
				"            position: relative;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .reviews {\r\n" + 
				"            color: #555;\r\n" + 
				"            font-weight: bold;\r\n" + 
				"            margin: 10px auto 20px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .notes {\r\n" + 
				"            color: #999;\r\n" + 
				"            font-size: 12px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .media .media-object {\r\n" + 
				"            max-width: 120px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .media-body {\r\n" + 
				"            position: relative;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .media-date {\r\n" + 
				"            position: absolute;\r\n" + 
				"            right: 25px;\r\n" + 
				"            top: 25px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .media-date li {\r\n" + 
				"            padding: 0;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .media-date li:first-child:before {\r\n" + 
				"            content: '';\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .media-date li:before {\r\n" + 
				"            content: '.';\r\n" + 
				"            margin-left: -2px;\r\n" + 
				"            margin-right: 2px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .media-comment {\r\n" + 
				"            margin-bottom: 20px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .media-replied {\r\n" + 
				"            margin: 0 0 20px 50px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .media-replied .media-heading {\r\n" + 
				"            padding-left: 6px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .btn-circle {\r\n" + 
				"            font-weight: bold;\r\n" + 
				"            font-size: 12px;\r\n" + 
				"            padding: 6px 15px;\r\n" + 
				"            border-radius: 20px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .btn-circle span {\r\n" + 
				"            padding-right: 6px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .embed-responsive {\r\n" + 
				"            margin-bottom: 20px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .tab-content {\r\n" + 
				"            padding: 50px 15px;\r\n" + 
				"            border: 1px solid #ddd;\r\n" + 
				"            border-top: 0;\r\n" + 
				"            border-bottom-right-radius: 4px;\r\n" + 
				"            border-bottom-left-radius: 4px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .custom-input-file {\r\n" + 
				"            overflow: hidden;\r\n" + 
				"            position: relative;\r\n" + 
				"            width: 120px;\r\n" + 
				"            height: 120px;\r\n" + 
				"            background: #eee url('https://s3.amazonaws.com/uifaces/faces/twitter/walterstephanie/128.jpg');\r\n" + 
				"            background-size: 120px;\r\n" + 
				"            border-radius: 120px;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        input[type=\"file\"] {\r\n" + 
				"            z-index: 999;\r\n" + 
				"            line-height: 0;\r\n" + 
				"            font-size: 0;\r\n" + 
				"            position: absolute;\r\n" + 
				"            opacity: 0;\r\n" + 
				"            filter: alpha(opacity=0);\r\n" + 
				"            -ms-filter: \"alpha(opacity=0)\";\r\n" + 
				"            margin: 0;\r\n" + 
				"            padding: 0;\r\n" + 
				"            left: 0;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .uploadPhoto {\r\n" + 
				"            position: absolute;\r\n" + 
				"            top: 25%;\r\n" + 
				"            left: 25%;\r\n" + 
				"            display: none;\r\n" + 
				"            width: 50%;\r\n" + 
				"            height: 50%;\r\n" + 
				"            color: #fff;\r\n" + 
				"            text-align: center;\r\n" + 
				"            line-height: 60px;\r\n" + 
				"            text-transform: uppercase;\r\n" + 
				"            background-color: rgba(0, 0, 0, .3);\r\n" + 
				"            border-radius: 50px;\r\n" + 
				"            cursor: pointer;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .custom-input-file:hover .uploadPhoto {\r\n" + 
				"            display: block;\r\n" + 
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
				"        .final-login>li {\r\n" + 
				"            color: #222 !important;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .final-login>li.active>a,\r\n" + 
				"        .final-login>li.active>a:hover,\r\n" + 
				"        .final-login>li.active>a:focus {\r\n" + 
				"            color: #fff;\r\n" + 
				"            background-color: #d14d42;\r\n" + 
				"            border: none !important;\r\n" + 
				"            border-bottom-color: transparent;\r\n" + 
				"            border-radius: none !important;\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"        .final-login>li>a {\r\n" + 
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
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"    <h1 class=\"title\">DringLow</h1>\r\n" + 
				"    <ul class=\"Horizontalbar\">\r\n" + 
				"        <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href=\"home.html\">Home</a></li>\r\n" + 
				"        <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href=\"course\">課程評價</a></li>\r\n" + 
				"        <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href=\"book\">二手書</a></li>\r\n" + 
				"        <li class=\"Horizontalborder\"><a class=\"Horizontalitem\" href=\"stuff.html\">新舊物品</a></li>\r\n" + 
				"        <li class=\"Horizontalborder\"><a class=\"active Horizontalitem\" href=\"gossip\">聊天</a></li>");
		//head 結束
		//body 開始
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
		out.println("</ul>");
		out.println("<div class=\"container\">\r\n" + 
				"        <div class=\"row\">\r\n" + 
				"            <div class=\"col-sm-10 col-sm-offset-1\" id=\"logout\">\r\n" + 
				"                <div class=\"page-header\">\r\n" + 
				"                    <h3 class=\"reviews\">留言板</h3>\r\n" + 
				"\r\n" + 
				"                </div>\r\n" + 
				"                <div class=\"comment-tabs\">\r\n" + 
				"                    <ul class=\"nav nav-tabs\" role=\"tablist\">\r\n" + 
				"                        <li class=\"active\"><a href=\"#comments-logout\" role=\"tab\" data-toggle=\"tab\"><h4 class=\"reviews text-capitalize\">留言數</h4></a></li>\r\n" + 
				"                        <li><a href=\"#add-comment\" role=\"tab\" data-toggle=\"tab\"><h4 class=\"reviews text-capitalize\">我要留言</h4></a></li>\r\n" + 
				"\r\n" + 
				"                    </ul>\r\n" + 
				"                    <div class=\"tab-content\">\r\n" + 
				"                        <div class=\"tab-pane active\" id=\"comments-logout\">\r\n" + 
				"                            <ul class=\"media-list\">");
		int count=1;
		for(List<String> l:list) {
			up=dbInterface.getGossip(Long.parseLong(l.get(0)));
			//{title,文章內容，發文者}
			down=dbInterface.getGossipReply(Long.parseLong(l.get(0)));
			//{人，留言}，{人，留言}，{人，留言}...........
			out.println("<li class=\"media\">\r\n" + 
					"\r\n" + 
					"        <div class=\"media-body\">\r\n" + 
					"            <div class=\"well well-lg\">\r\n" + 
					"                <h4 class=\"media-heading text-uppercase reviews\">"+l.get(3)+"</h4>\r\n" + 
					"                <p class=\"media-comment\">\r\n" + 
					tranRNtoBR(up.get(1))+ 
					"                </p>\r\n" + 
					"                <a class=\"btn btn-circle btn-primary\"><span class=\"glyphicon  glyphicon-user\"></span>"+l.get(2)+"</a>\r\n" + 
					"                <a class=\"btn btn-info btn-circle text-uppercase pull-right\" role=\"reply\" id='"+l.get(0)+"'><span class=\"glyphicon glyphicon-share-alt\"></span> 回復</a>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"                <a class=\"btn btn-warning btn-circle text-uppercase\" data-toggle=\"collapse\" href=\"#reply"+(count)+"\"><span class=\"glyphicon glyphicon-comment\"></span>"+l.get(1)+"評論</a>\r\n" + 
					"            </div>\r\n" + 
					"        </div>");
			out.println("<div class=\"collapse\" id=\"reply"+(count)+"\">\r\n" + 
					"            <ul class=\"media-list\">");
			for(List<String> peoples:down) {
				out.println("<li class=\"media media-replied\">\r\n" + 
						"                    <div class=\"media-body\">\r\n" + 
						"                        <div class=\"well well-lg\">\r\n" + 
						"                            <h4 class=\"media-heading text-uppercase reviews\"><span class=\"glyphicon glyphicon-share-alt\"></span> "+peoples.get(0)+"</h4>\r\n" + 
						"                            <p class=\"media-comment\">\r\n" + 
						tranRNtoBR(peoples.get(1)) + 
						"                            </p>\r\n" + 
						"                        </div>\r\n" + 
						"                    </div>\r\n" + 
						"                </li>");
			}
			out.println("</ul>\r\n" + 
					"        </div>\r\n" + 
					"    </li>");
			count++;
		}
		out.println("</ul>\r\n" + 
				"                        </div>\r\n" + 
				"                        <div class=\"tab-pane\" id=\"add-comment\">\r\n" + 
				"                            <form method=\"post\" data-toggle=\"validator\" class=\"form-horizontal\" id=\"commentForm\" role=\"form\" action=\"gossip/message/po\">\r\n" + 
				"                                <div class=\"form-group\">\r\n" + 
				"                                    <label for=\"addTitle\" class=\"col-sm-2 control-label\">標題</label>\r\n" + 
				"                                    <div class=\"col-sm-10\">\r\n" + 
				"                                        <textarea class=\"form-control\" data-minlength=\"1\" maxlength=\"20\" name=\"title\" id=\"addTitle\" rows=\"1\" required></textarea>\r\n" + 
				"                                        <p id=\"passwordHelpBlock\" class=\"form-text text-muted\">\r\n" + 
				"                                            請輸入1~20字(不包含空格)\r\n" + 
				"                                        </p>\r\n" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                                <div class=\"form-group\">\r\n" + 
				"                                    <label for=\"addComment\" class=\"col-sm-2 control-label\">內容</label>\r\n" + 
				"                                    <div class=\"col-sm-10\">\r\n" + 
				"                                        <textarea class=\"form-control\" data-minlength=\"10\" maxlength=\"200\" name=\"content\" id=\"addComment\" rows=\"5\" required></textarea>\r\n" + 
				"                                        <p id=\"passwordHelpBlock\" class=\"form-text text-muted\">\r\n" + 
				"                                            請輸入10~200字(不包含空格)\r\n" + 
				"                                        </p>\r\n" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                                <div class=\"form-group\">\r\n" + 
				"                                    <div class=\"col-sm-offset-2 col-sm-10\">\r\n" + 
				"                                        <button class=\"btn btn-success btn-circle text-uppercase\" type=\"submit\" id=\"submitComment\"><span class=\"glyphicon glyphicon-send\"></span>上傳評論</button>\r\n" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </form>\r\n" + 
				"                        </div>\r\n" + 
				"\r\n" + 
				"                        <!-- Modal -->\r\n" + 
				"                        <div class=\"modal fade\" id=\"myModal\" role=\"dialog\">\r\n" + 
				"                            <div class=\"modal-dialog\">\r\n" + 
				"                                <!-- Modal content-->\r\n" + 
				"                                <div class=\"modal-content\">\r\n" + 
				"                                    <div class=\"modal-header\">\r\n" + 
				"                                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n" + 
				"                                        <h4 class=\"modal-title\" id=\"curtitle\">回復</h4>\r\n" + 
				"                                    </div>\r\n" + 
				"                                    <div class=\"modal-body\">\r\n" + 
				"                                        <form method=\"post\" action=\"gossip/message/re\" id=\"mySubForm\" data-toggle=\"validator\">\r\n" + 
				"\r\n" + 
				"                                            <label for=\"comment\">想說的話：</label>\r\n" + 
				"                                            <textarea class=\"form-control\" data-minlength=\"10\" maxlength=\"120\" name=\"comment\" rows=\"5\" id=\"comment\" required></textarea>\r\n" + 
				"                                            <p id=\"passwordHelpBlock\" class=\"form-text text-muted\">\r\n" + 
				"                                                請輸入10~120字(不包含空格)\r\n" + 
				"                                            </p>\r\n" + 
				"                                            <br>\r\n" + 
				"                                            <input class=\"btn btn-info btn-lg  center-block\" onClick=\"check()\" style=\"width:100%\" type=\"submit\" value=\"Submit\">\r\n" + 
				"                                            <input style=\"width:100%\" type=\"hidden\" name=\"id\" value=\"-1\">\r\n" + 
				"                                        </form>\r\n" + 
				"                                    </div>\r\n" + 
				"                                    <div class=\"modal-footer\">\r\n" + 
				"                                        <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\r\n" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                        <!-- Modal end -->\r\n" + 
				"                        <!-- login Modal -->\r\n" + 
				"                        <div class=\"modal fade\" id=\"loginModal\" role=\"dialog\">\r\n" + 
				"                            <div class=\"modal-dialog\">\r\n" + 
				"                                <!-- Modal content-->\r\n" + 
				"                                <div class=\"modal-content\">\r\n" + 
				"                                    <div class=\"modal-header\">\r\n" + 
				"                                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n" + 
				"                                        <h4 class=\"modal-title\" id=\"curtitle\">登入</h4>\r\n" + 
				"                                    </div>\r\n" + 
				"                                    <div class=\"modal-body\">\r\n" + 
				"                                        <div class=\"form-body\" style=\"display:block\">\r\n" + 
				"                                            <ul class=\"nav nav-tabs final-login\">\r\n" + 
				"                                                <li class=\"active\"><a data-toggle=\"tab\" href=\"#sectionA\">登入</a></li>\r\n" + 
				"                                                <li><a data-toggle=\"tab\" href=\"#sectionB\">註冊</a></li>\r\n" + 
				"                                            </ul>\r\n" + 
				"                                            <div class=\"tab-content\">\r\n" + 
				"                                                <div id=\"sectionA\" class=\"tab-pane fade in active\">\r\n" + 
				"                                                    <div class=\"innter-form\">\r\n" + 
				"                                                        <form class=\"sa-innate-form\" method=\"post\" id=\"loginform\" data-toggle=\"validator\" role=\"form\" action=\"login.do\">\r\n" + 
				"                                                            <div class=\"form-group\">\r\n" + 
				"                                                                <label for=\"inputEmail\" class=\"control-label\">Email</label>\r\n" + 
				"                                                                <input type=\"email\" name=\"email\" class=\"form-control\" id=\"inputEmail\" placeholder=\"Email\" required>\r\n" + 
				"                                                                <div class=\"help-block with-errors\"></div>\r\n" + 
				"                                                            </div>\r\n" + 
				"                                                            <div class=\"form-group\">\r\n" + 
				"                                                                <label for=\"inputPassword\" class=\"control-label\">密碼</label>\r\n" + 
				"                                                                <div class=\"form-group\">\r\n" + 
				"                                                                    <input type=\"password\" name=\"password\" class=\"form-control\" data-minlength=\"6\" maxlength=\"16\" id=\"inputPassword\" placeholder=\"Password\" required>\r\n" + 
				"                                                                    <div class=\"help-block\"></div>\r\n" + 
				"                                                                </div>\r\n" + 
				"                                                            </div>\r\n" + 
				"                                                            <button type=\"submit\">登入</button>\r\n" + 
				"                                                        </form>\r\n" + 
				"                                                    </div>\r\n" + 
				"                                                    <div class=\"clearfix\"></div>\r\n" + 
				"                                                </div>\r\n" + 
				"                                                <div id=\"sectionB\" class=\"tab-pane fade\">\r\n" + 
				"                                                    <div class=\"innter-form\">\r\n" + 
				"                                                        <form class=\"sa-innate-form\" method=\"post\" id=\"signinform\" data-toggle=\"validator\" role=\"form\" action=\"register.do\">\r\n" + 
				"                                                            <div class=\"form-group\">\r\n" + 
				"                                                                <label for=\"inputEmail2\" class=\"control-label\">Email</label>\r\n" + 
				"                                                                <input type=\"email\" name=\"email\" class=\"form-control\" id=\"inputEmail2\" placeholder=\"Email\" required>\r\n" + 
				"                                                                <div class=\"help-block with-errors\"></div>\r\n" + 
				"                                                            </div>\r\n" + 
				"                                                            <div class=\"form-group\">\r\n" + 
				"                                                                <label for=\"inputPassword2\" class=\"control-label\">密碼</label>\r\n" + 
				"                                                                <div class=\"form-group\">\r\n" + 
				"                                                                    <input type=\"password\" name=\"password\" class=\"form-control\" data-minlength=\"6\" maxlength=\"16\" id=\"inputPassword2\" placeholder=\"Password\" required>\r\n" + 
				"                                                                    <div class=\"help-block\">6~16字</div>\r\n" + 
				"                                                                </div>\r\n" + 
				"                                                                <label for=\"inputPasswordConfirm\" class=\"control-label\">請再次輸入密碼</label>\r\n" + 
				"                                                                <div class=\"form-group\">\r\n" + 
				"                                                                    <input type=\"password\" name=\"confirmedpassword\" class=\"form-control\" id=\"inputPasswordConfirm\" maxlength=\"16\" data-match=\"#inputPassword2\" data-match-error=\"兩次輸入密碼不同\" placeholder=\"Confirm\" required>\r\n" + 
				"                                                                    <div class=\"help-block with-errors\"></div>\r\n" + 
				"                                                                </div>\r\n" + 
				"                                                            </div>\r\n" + 
				"                                                            <button type=\"submit\">註冊</button>\r\n" + 
				"                                                        </form>\r\n" + 
				"                                                    </div>\r\n" + 
				"                                                </div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                        </div>\r\n" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                        <!-- login Modal end -->\r\n" + 
				"                        <div class=\"page-header text-center\">\r\n" + 
				"                            <h3 class=\"reviews\"> DringLow </h3>\r\n" + 
				"                        </div>\r\n" + 
				"                    </div>\r\n" + 
				"</body>\r\n" + 
				"\r\n" + 
				"<script>\r\n" + 
				"    $(document).ready(function() {\r\n" + 
				"        $(\"a.Horizontalitem\").click(function(event) {\r\n" + 
				"            var item = this;\r\n" + 
				"            if (item.textContent == 'Sign in')\r\n" + 
				"                $(\"#loginModal\").modal();\r\n" + 
				"        });\r\n" + 
				"        $(\"a[role='reply']\").click(function(event) {\r\n" + 
				"            $(\"#myModal\").modal();\r\n" + 
				"            $(\"input[type='hidden']\")[0].value = this.id;\r\n" + 
				"        });\r\n" + 
				"    });\r\n" + 
				"</script>\r\n" + 
				"\r\n" + 
				"</html>");
		

		out.close();
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
        out.println("<meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
		out.println("<title>聊天- DringLow</title>");
        out.println("</head>");
		out.println("<body>");
		for(List<String> l:list) {
			out.println("<a href='gossip/in?id="+l.get(0)+"'>"+l.get(3)+"</a>");
			out.println(" "+l.get(2)+" "+l.get(1)+"<br>");
		}
		
		out.println("</body>");
		out.println("</html>");*/
}
