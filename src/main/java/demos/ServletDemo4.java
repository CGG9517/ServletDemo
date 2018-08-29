package demos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

// @WebServlet("/HelloForm") // 使用此注解，可以不在web.xml中配置映射
public class ServletDemo4 extends HttpServlet {

    private static final long serialVersionUID = 5239867234650374647L;
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


        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String title = "使用 GET 方法读取表单数据";

        String docType = "<!DOCTYPE html> \n";

        String name = req.getParameter("name");
        String url = req.getParameter("url");

        Cookie nameCookie = new Cookie("name", name);
        Cookie urlCookie = new Cookie("url", url);
        nameCookie.setMaxAge(60*60*24);
        urlCookie.setMaxAge(60*60*24);

        resp.addCookie(nameCookie);
        resp.addCookie(urlCookie);

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>站点名</b>：" + name + "\n" +
                "  <li><b>网址</b>：" + url + "\n" +
                "</ul>\n" +
                "</body></html>");
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        System.out.println("使用ServletDemo3的doPost方法啦");
        doGet(req, resp);
    }
}
