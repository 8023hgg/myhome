<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

  <head>
      <title>上传文件主页</title>
      <script src="${contextPath}/resources/js/jquery-2.0.0.js"></script>
      <script src="${contextPath}/resources/js/file/file_list.js"></script>
  </head>
  <body>
    <input type="hidden" value="${contextPath}" id="contextPath"/>
    <div id="formDiv"></div>
    <table>
      <tr>
        <td>文件ID</td>
        <td>文件名称</td>
        <td>上传日期</td>
        <td>操作</td>
      </tr>
      <c:if test="${!empty files}">
        <c:forEach items="${files}" var="file">
          <tr>
            <td>${file.id}</td>
            <td>${file.name}</td>
            <td>${file.createDate}</td>
            <td><a href="javascript:;" class="del" rel="${file.fid}">删除</a></td>
            <td><a href="javascript:;" rel="${file.fid}" class="download">下载</a></td>
          </tr>
        </c:forEach>
      </c:if>
    </table>
    <a href="${contextPath}/file/index">上传文件</a>
  </body>
</html>
