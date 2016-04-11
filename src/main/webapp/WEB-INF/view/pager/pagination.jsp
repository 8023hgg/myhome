<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <body>
        <div class="panelBar">
            <div class="pages">
                <span>显示</span> <select class="combox" name="numPerPage"
                                        onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="20" <c:if test="${pagination.pageSize==20}">selected="selected"</c:if>>20</option>
                <option value="50" <c:if test="${pagination.pageSize==50}">selected="selected"</c:if>>50</option>
                <option value="100" <c:if test="${pagination.pageSize==100}">selected="selected"</c:if>>100</option>
                <option value="200" <c:if test="${pagination.pageSize==200}">selected="selected"</c:if>>200</option>
            </select> <span>条，共${pagination.totalCount}条</span>
            </div>
            <div class="pagination" targetType="navTab" totalCount="${pagination.totalCount?}" numPerPage="${pagination.pageSize}" pageNumShown="10" currentPage="${pagination.pageNo}"></div>
        </div>
    </body>
</html>
