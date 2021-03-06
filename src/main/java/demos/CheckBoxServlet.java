package demos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Class: CheckBoxServlet
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/28
 */
@WebServlet("/CheckBox")
public class CheckBoxServlet extends HttpServlet {
    private static final long serialVersionUID = 787629050922809606L;

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String title = "读取复选框数据";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>菜鸟按教程标识：</b>: "
                + req.getParameter("runoob") + "\n" +
                "  <li><b>Google 标识：</b>: "
                + req.getParameter("google") + "\n" +
                "  <li><b>淘宝标识：</b>: "
                + req.getParameter("taobao") + "\n" +
                "</ul>\n" +
                "</body></html>");

    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        doGet(req, resp);
    }
}
