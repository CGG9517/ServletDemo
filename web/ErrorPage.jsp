<%--
  Created by IntelliJ IDEA.
  User: Jiang Chao
  Date: 2018/8/30
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>错误</title>
</head>
<body>
// 获取属性
${pageContext.requestl.getParameter("")}

    <%
        response.sendError(418, "I'm a teapot");
//        response.sendError(407, "Need authentication!!!" );
    %>

</body>
</html>
