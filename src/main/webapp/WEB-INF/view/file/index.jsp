<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>上传文件主页</title>
  </head>
  <body>
    <form action="${contextPath}/file/load" method="post" enctype="multipart/form-data">
      <input type="file" value="点击上传文件" name="file"/>
      <input type="submit" value="提交"/>
    </form>
  </body>
</html>
