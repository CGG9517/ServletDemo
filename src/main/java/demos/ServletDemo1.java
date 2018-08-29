package demos;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Class: ServletDemo1
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/27
 */
public class ServletDemo1 implements Servlet {
    @Override
    public void init( ServletConfig servletConfig ) throws ServletException {
        System.out.println("==========init===========");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /*
     * 对客户端响应方法
     * 每次请求该servlet都会执行该方法
     */
    @Override
    public void service( ServletRequest servletRequest, ServletResponse servletResponse ) throws ServletException, IOException {
        System.out.println("ServletDemo1请求啦！");
    }

    @Override
    public String getServletInfo() {
        return "ServletDemo1 by JiangChao";
    }

    @Override
    public void destroy() {
        System.out.println("*************销毁啦************");
    }
}
