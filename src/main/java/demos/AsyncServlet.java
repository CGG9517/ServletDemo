package demos;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Class: AsyncServlet
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/31
 */
@WebServlet(urlPatterns = {"/asyncServlet"}, asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    private static final long serialVersionUID = 6098060853059855492L;

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        final AsyncContext asyncContext = req.getAsyncContext(); // 启动异步处理上下文

        req.startAsync();
        asyncContext.start(() -> {
            // 此处是异步代码
            asyncContext.complete();
        });
    }
}
