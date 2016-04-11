<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <script src="${contextPath}/resources/js/pager/pagination.js"></script>
    <body>
        <div>
            <div>
                <span>显示</span>
                <select onchange="changePageNum(this.value)">
                    <option value="20" <c:if test="${pagination.pageSize==20}">selected="selected"</c:if>>20</option>
                    <option value="50" <c:if test="${pagination.pageSize==50}">selected="selected"</c:if>>50</option>
                    <option value="100" <c:if test="${pagination.pageSize==100}">selected="selected"</c:if>>100</option>
                    <option value="200" <c:if test="${pagination.pageSize==200}">selected="selected"</c:if>>200</option>
                </select>
                <span>条，共${pagination.totalCount}条</span>
            </div>
        </div>
    </body>
</html>
