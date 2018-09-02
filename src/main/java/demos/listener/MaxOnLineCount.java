package demos.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Class: OnlineCOunt
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/31
 */
@WebListener
public class MaxOnLineCount implements HttpSessionListener {
    @Override
    public void sessionCreated( HttpSessionEvent se ) {
        ServletContext cx = se.getSession().getServletContext();
        Integer onlineCount = Integer.valueOf(cx.getAttribute("onlineCount").toString());
        cx.setAttribute("onlineCount", ++onlineCount);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int maxOnLineCount = Integer.parseInt(cx.getAttribute("maxOnlineCount").toString());
        if (onlineCount > maxOnLineCount)
        {
            cx.setAttribute("maxOnlineCount", onlineCount);
            String now = sdf.format(new Date());
            cx.setAttribute("date", now);
            System.out.println("最高在线人数：" + onlineCount);
            System.out.println("时间：" + now);
        }

    }

    @Override
    public void sessionDestroyed( HttpSessionEvent se ) {

        ServletContext ctx = se.getSession().getServletContext();
        Integer onlineCount = Integer.valueOf(ctx.getAttribute("onlineCount").toString());

        ctx.setAttribute("onlineCount", --onlineCount);

    }
}
