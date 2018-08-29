package demos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @Class: ReadParamsServlet
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/28
 */
@WebServlet("/ReadParams")
public class ReadParamsServlet extends HttpServlet {

    private static final long serialVersionUID = 1196854046925249858L;

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter writer = resp.getWriter();
        String title = "读取所有的表单数据";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        writer.println(docType +
                "<html>\n" +
                "<head><meta charset=\"utf-8\"><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "<th>参数名称</th><th>参数值</th>\n"+
                "</tr>\n");

        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements())
        {
            String name = names.nextElement();
            String[] parameterValues = req.getParameterValues(name);
            if (parameterValues.length == 1)
            {
                String value = parameterValues[0];
                if (value.length() == 0)
                    writer.println("<tr>" +
                            "<td>" + name + "</td>" +
                            "<td>没有值</td>" +
                            "</tr>\n"
                    );
                else
                    writer.println("<tr>" +
                            "<td>" + name + "</td>" +
                            "<td>" + value + "</td>" +
                            "</tr>\n"
                    );
            }
            else {
                writer.println("<tr>" +
                        "<td>" + name + "</td>" +
                        "<td><ul>" );

                for(String value : parameterValues)
                {
                    writer.println( "<li>" +
                            value + "</li>");

                }
                writer.println("</ul>\n</td>\n</tr>");
            }
        }

        writer.println("\n</table>\n");
        printHeader(writer, req, resp);

        writer.println("</table>\n</body>\n</html>"

        );

    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void printHeader(PrintWriter writer, HttpServletRequest req, HttpServletResponse resp)
    {
        writer.println("<table width=\"100%\" border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "<th>Header 名称</th><th>Header 值</th>\n"+
                "</tr>\n");

        Enumeration headerNames = req.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String paramName = (String)headerNames.nextElement();
            writer.print("<tr><td>" + paramName + "</td>\n");
            String paramValue = req.getHeader(paramName);
            writer.println("<td> " + paramValue + "</td></tr>\n");
        }
    }
}
