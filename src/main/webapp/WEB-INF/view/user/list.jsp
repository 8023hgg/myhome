<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>用户列表</title>
        <script src="${contextPath}/resources/js/jquery-2.0.0.js"></script>
    </head>
    <body>
        <form action="${contextPath}/user/list" method="post">
            <input name="pageNo" type="hidden" value="${pagination.pageNo}"/>
            <input name="pageSize" type="hidden" value="${pagination.pageSize}"/>
            <input name="userName" type="text" value="${userModel.userName}"/>
            <input type="submit" value="提交表单"/>
        </form>
        <table>
            <tr>
                <td>用户名称</td>
                <td>最近登录时间</td>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.userName}</td>
                    <td>${user.lastLoginDate}</td>
                </tr>
            </c:forEach>
        </table>
        <c:import url="../pager/pagination.jsp"/>
    </body>
</html>
