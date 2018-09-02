package demos.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Class: VisitCountFilete
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/31
 */

@WebFilter(urlPatterns = {"/*"}, initParams = {@WebInitParam(name = "encoding", value = "utf-8")})
public class VisitCountFilter implements Filter {
    private ExecutorService exeService = Executors.newSingleThreadExecutor();
    private Properties visitLog;
    private File logFile;

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        String appPath = filterConfig.getServletContext().getRealPath("/");
        logFile = new File(appPath, "visitlog.txt");

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        visitLog = new Properties();
        /**
         * 导入访问次数
         */
        try {
            visitLog.load(new FileReader(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        final String uri = req.getRequestURI();

        exeService.execute(() -> {
            String value = visitLog.getProperty(uri);
            if (value == null) {
                visitLog.setProperty(uri, "1");
            } else {
                Integer count = Integer.valueOf(visitLog.getProperty(uri));
                visitLog.setProperty(uri, String.valueOf(++count));
            }
            try {
                visitLog.store(new FileWriter(logFile), "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        exeService.shutdown();
    }
}
