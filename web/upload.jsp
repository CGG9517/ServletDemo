<%--
  Created by IntelliJ IDEA.
  User: Jiang Chao
  Date: 2018/8/29
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传实例 - 菜鸟教程</title>
</head>
<body>
    <h1>文件上传实例 - 菜鸟</h1>
    <form method="post" action="/UploadServlet"  enctype="multipart/form-data">
        选择文件夹：
        <input type="file" name="uploadFile" id="uploadFile">
        <input type="submit" value="上传">
    </form>

</body>
</html>
