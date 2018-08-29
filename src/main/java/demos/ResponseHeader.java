package demos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.logging.SimpleFormatter;

/**
 * @Class: ResponseHeader
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/28
 */
@WebServlet("/Refresh")
public class ResponseHeader extends HttpServlet {

    private static final long serialVersionUID = -1787890492221174693L;

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        System.out.println("访问get啦");
        resp.setContentType("text/html;charset=utf-8");

        // 设置刷新自动加载时间为 5 秒
        resp.setIntHeader("Refresh", 5);
        //使用默认时区和语言环境获得一个日历
        Calendar cal = Calendar.getInstance();

        Date time = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        String nowTime = sdf.format(time);

        String docType = "<!DOCTYPE html>\n";

        PrintWriter writer = resp.getWriter();
        String title = "自动刷新 Header 设置 - 菜鸟教程实例";

        writer.println( docType +
                        "<html>\n" +
                        "<head>\n" +
                        "<title>" + title + "</title>" +
                        "</head>\n" +
                        "<body bgcolor='#f0f0f0'>" +
                        "<h1 align=\"center\">" + title + "</h1>\n" +
                        "<p>当前时间是：" + nowTime + "</p>\n" +
                        "</body>" +
                        "</html>"
                );

    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        doGet(req, resp);
    }
}
