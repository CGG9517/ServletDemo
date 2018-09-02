package demos.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Class: TimeTag
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/8/31
 */
public class TimeTag extends TagSupport {
    private static final long serialVersionUID = 9197597380789121590L;
    private String format = "yyyy-MM-dd HH:mm:ss";
    private String foreColor = "black";
    private String backColor = "white";


    @Override
    public int doStartTag() throws JspException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        JspWriter writer = pageContext.getOut();
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("<span style='color:%s;background-color:%s;'>%s</span>",
                foreColor, backColor, sdf.format(new Date())));

        try {
            writer.print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat( String format ) {
        this.format = format;
    }

    public String getForeColor() {
        return foreColor;
    }

    public void setForeColor( String foreColor ) {
        this.foreColor = foreColor;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor( String backColor ) {
        this.backColor = backColor;
    }
}
