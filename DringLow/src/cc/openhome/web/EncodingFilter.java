package cc.openhome.web;

import java.io.IOException;

import javax.jws.soap.InitParam;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.sun.javafx.sg.prism.web.NGWebView;

@WebFilter(urlPatterns = {"/*"}, 
		initParams = { @WebInitParam(name = "ENCODING", value = "UTF-8") })
public class EncodingFilter implements Filter {
	private String ENCODING;

	public void init(FilterConfig fConfig) throws ServletException {
		ENCODING = fConfig.getInitParameter("ENCODING");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if ("GET".equals(req.getMethod())) {
			req = new EncodingWrapper(req, ENCODING);
		} else {
			req.setCharacterEncoding(ENCODING);
		}
		chain.doFilter(req, response);
	}

	public void destroy() {
	}
}
