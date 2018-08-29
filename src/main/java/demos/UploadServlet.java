package demos;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Class: UploadServlet
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/29
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = -3949970122413073022L;

    public static final String UPLOAD_DIRECTORY = "upload";
    private static final int UPLOAD_THREHOLD = 1024 * 1024 * 3;
    public static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;


    /**
     * 上传数据及保存文件
     */
    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(req)) {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.print("Error：表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(UPLOAD_THREHOLD);

        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload fileUpload = new ServletFileUpload();
        fileUpload.setFileItemFactory(factory);
        // 设置最大文件上传值
        fileUpload.setFileSizeMax(MAX_FILE_SIZE);
        // 设置最大请求值 (包含文件和表单数据)
        fileUpload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        fileUpload.setHeaderEncoding("utf-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = req.getServletContext().getRealPath("./") + UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }


        try {

            // 解析请求的内容提取文件数据
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            if (fileItems != null && fileItems.size() > 0) {
                for (FileItem each : fileItems) {

                    // 处理表单中的字段 简单类型返回true， upload file返回false
                    if (!each.isFormField()) {
                        String fileName = new File(each.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;

                        File storeFile = new File(filePath);

                        System.out.println("保存文件至：" + filePath);

                        // 保存文件到硬盘
                        each.write(storeFile);
                        req.setAttribute("message", "文件上传成功");
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", e.getMessage());
        }
        req.getRequestDispatcher("/message.jsp").forward(req, resp);

    }
}
