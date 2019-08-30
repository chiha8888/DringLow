package cc.openhome.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cc.openhome.model.Connector;
import cc.openhome.model.DBInterface;
import cc.openhome.model.UserService;
import javafx.scene.Scene;


@WebListener
public class DringLowListener implements ServletContextListener {
	public DBInterface dbInterface;
    public void contextInitialized(ServletContextEvent arg0)  { 
         ServletContext context= arg0.getServletContext();
         String users=context.getInitParameter("USERS");
         context.setAttribute("userService", new UserService(users));
         dbInterface=new Connector();
         context.setAttribute("dbInterface", dbInterface);
         
    }
    
	public void contextDestroyed(ServletContextEvent arg0)  { 
		dbInterface.Close();
    }
}
