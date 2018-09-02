package demos.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Class: InitListener
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/31
 */
@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized( ServletContextEvent sce ) {
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("maxOnlineCount", 0);
        ctx.setAttribute("onlineCount", 0);
    }

    @Override
    public void contextDestroyed( ServletContextEvent sce ) {


    }
}
