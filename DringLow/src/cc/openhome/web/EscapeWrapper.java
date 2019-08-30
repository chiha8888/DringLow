package cc.openhome.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EscapeWrapper extends HttpServletRequestWrapper{

	public EscapeWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		String value=getRequest().getParameter(name);
		return EscapeWrapper.htmlEncode(value);
	}
	
	public static String htmlEncode(String source) {
        if (source == null) {
            return "";
        }
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
            case '<':
                buffer.append("&lt;");
                break;
            case '>':
                buffer.append("&gt;");
                break;
            case '&':
                buffer.append("&amp;");
                break;
            case '"':
                buffer.append("&quot;");
                break;
            /*case 10:	//'\n'
            	break;
            case 13:	//'\r'
                break;*/
            default:
                buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }
}
