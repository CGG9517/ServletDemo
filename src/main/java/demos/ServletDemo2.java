package demos;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @Class: ServletDemo2
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/27
 */
public class ServletDemo2 extends GenericServlet {
    private static final long serialVersionUID = -1498544300893236306L;

    @Override
    public void service( ServletRequest req, ServletResponse res ) throws ServletException, IOException {
        System.out.println("请求ServletDemo2啦");
    }
}
