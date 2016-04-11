<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
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
<c:import url="/pager/pagination.jsp"/>
</body>
</html>
