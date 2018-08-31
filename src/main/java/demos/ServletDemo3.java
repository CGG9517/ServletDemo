package demos;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Class: ServletDemo3
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/27
 */

@WebServlet("/hello-world") // 使用此注解，可以不在web.xml中配置映射
public class ServletDemo3 extends HttpServlet {
    private static final long serialVersionUID = -2815107232141792132L;
    private String message;

    @Override
    public void destroy() {
        System.out.println("servlet销毁啦");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("ServletDemo3初始化啦");
        message = "hello world";
    }

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        System.out.println("使用ServletDemo3的doGet方法啦");
        resp.setContentType("text/html");

        String url = "http://www.runoob.com";
        resp.sendRedirect(url);
     /*   PrintWriter writer = resp.getWriter();

        writer.println("<h1>" + message + "</h1>");*/
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        System.out.println("使用ServletDemo3的doPost方法啦");
        super.doPost(req, resp);
    }
}
