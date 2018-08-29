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
 * @Class: CookieServlet
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/29
 */
@WebServlet("/ReadCookies")
public class CookieServlet extends HttpServlet {
    private static final long serialVersionUID = -4170576699671365964L;

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Cookie[] cookies = req.getCookies();

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String docType = "<!DOCTYPE html>\n";
        String title = "Read Cookie";
        out.println(docType + "" +
                "<html>\n" +
                "<head>\n" +
                "<title>" + title + "</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n");

        if (cookies.length > 0)
            out.println("<h2>Cookie 名称和值</h2>\n" +
                    "<ul>\n");

        for (Cookie each : cookies) {
            if (each.getName().compareTo("name") == 0) {
                // maxAge设置为0，删除cookie
                each.setMaxAge(0);
                resp.addCookie(each);

            }
            out.println("<li>" + each.getName() + "=" + each.getValue() + "</li>\n");
        }

        out.println("</ul>\n<body>\n</html>\n");
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        doGet(req, resp);
    }
}
