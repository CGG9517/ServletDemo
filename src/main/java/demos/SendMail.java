package demos;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @Class: SendMail
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/29
 */
@WebServlet("/SendMail")
public class SendMail extends HttpServlet {

    private static final long serialVersionUID = -7016960510855839726L;

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        String to = "jiangchaohihi@163.com";
        String from = "jiangchaohihi@outlook.com";
        String host = "smtp.office365.com";
        String passowrd = "qingmu131524~";


        Properties properties = new Properties();
        System.out.println(this.getClass().getResource("/").getPath());
//        String propsPath = getServletContext().getRealPath("mailConfig.properties").replace("\\", "/");
//        System.out.println(propsPath);
        FileInputStream in = new FileInputStream(this.getClass().getResource("/").getPath() + "mailConfig.properties");
//        InputStream in = new FileInputStream("F:\\idea_workspace\\ServletDemo\\src\\main\\resources\\mailConfig.properties");
        properties.load(in);
        /*
        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.socketFactory", sf);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }*/

        // 获取默认的 Session 对象
        Session session = Session.getInstance(properties);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Transport transport = null;
        try {
            transport = session.getTransport();
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，
            // 发件人需要提交邮箱的用户名和密码给smtp服务器，用户名
            // 和密码都通过验证之后才能够正常发送邮件给收件人。

            transport.connect(host, from, passowrd);
            MimeMessage mimeMessage = new MimeMessage(session);
            // 设置 From: header field of the header.
            mimeMessage.setFrom(new InternetAddress(from));

            // 设置 To: header field of the header.
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 设置 Subject: header field
            mimeMessage.setSubject("This is an important test");
            // 现在设置实际消息
            mimeMessage.setText("Now let's begin...");
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();


            String title = "发送电子邮件";
            String res = "成功发送消息...";
            String docType = "<!DOCTYPE html> \n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<p align=\"center\">" + res + "</p>\n" +
                    "</body></html>");


        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
