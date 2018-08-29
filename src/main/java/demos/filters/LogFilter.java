package demos.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @Class: LogFilter
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/28
 */

public class LogFilter implements Filter {
    private HashMap<String, String> users = new HashMap<>();

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        String site = filterConfig.getInitParameter("Site");
        System.out.printf("***网站：%s 过滤器初始化***\n ", site);

        users.put("jiangchao", "19941224");

    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {

        /*HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String username = request.getParameter("username");
        String pswd = request.getParameter("password");

        if (pswd != null && pswd.equals(users.get(username))){
            chain.doFilter(request, response);
        }
        else
        {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            String title = "尚未登陆";
            String docType = "<!DOCTYPE html> \n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "</body></html>");
        }*/
        System.out.println("过滤中");
        chain.doFilter(request, response);


    }

    @Override
    public void destroy() {

    }
}
