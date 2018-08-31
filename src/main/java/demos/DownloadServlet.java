package demos;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Class: DowloadServlet
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/29
 */
@WebServlet("/DownloadFile")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = -634507258247604153L;

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        // 采用流的形式
        req.setCharacterEncoding("utf-8");
        String fileName = req.getParameter("name");
        String id = req.getSession().getId();
        System.out.println("sessionId: " + id + "正在请求下载文件：" + fileName);

        //设置文件MIME类型
        resp.setContentType(getServletContext().getMimeType(fileName));

        //设置Content-Disposition, 注意中文乱码问题
        resp.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));

        //获取要下载的文件绝对路径，我的文件都放到目录下
        String filePath = getServletContext().getRealPath("/download/" + fileName);

        FileInputStream in = new FileInputStream(filePath);
        ServletOutputStream out = resp.getOutputStream();

        byte[] bytes = new byte[1024];
        int len;
        while ((len = in.read(bytes)) != -1)
        {
            out.write(bytes, 0, len);
        }

        in.close();
        out.close();
    }
}
