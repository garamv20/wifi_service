<%@ page import="service.BookmarkService" %><%--
  Created by IntelliJ IDEA.
  User: garam
  Date: 2023/12/18
  Time: 7:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    boolean isSuccess = BookmarkService.deleteBookmark(id);
    if(isSuccess){
        response.sendRedirect("showBookmark.jsp");
    }else {
%>
<script>
    alert("삭제에 실패했습니다.");
    history.go(-1);
</script>
<%
    }
%>
