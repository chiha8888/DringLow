package cc.openhome.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebFilter(urlPatterns= {"/member.view", "/logout.do" ,"/message.do","/course/message/re","/salebook.do","/gossip/message/re",
						"/gossip/message/po"},
			initParams= {@WebInitParam(name="LOGIN",value="/DringLow/LOGIN.html")}
			)
public class MemberFilter implements Filter {
	private String LOGIN;
	
	public void init(FilterConfig fConfig) throws ServletException {
		LOGIN=fConfig.getInitParameter("LOGIN");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		if(req.getSession().getAttribute("login")!=null) {
			chain.doFilter(request, response);
		}
		else {
			//需要登入
			resp.sendRedirect(LOGIN);
		}
	}
	
	public void destroy() {
		
	}
}
