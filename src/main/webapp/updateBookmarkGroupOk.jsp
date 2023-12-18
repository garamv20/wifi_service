<%@ page import="service.BookmarkService" %><%--
  Created by IntelliJ IDEA.
  User: garam
  Date: 2023/12/18
  Time: 6:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bookmarkGroup" class="dto.BookmarkGroup"></jsp:useBean>
<jsp:setProperty property="*" name="bookmarkGroup"/>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    bookmarkGroup.setId(id);
    int re = BookmarkService.updateBookmarkGroup(bookmarkGroup);
    if(re > 0){
        response.sendRedirect("manageBookmarkGroup.jsp");
    }else {
%>
<script>
    alert("수정에 실패했습니다.");
    history.go(-1);
</script>
<%
    }
%>
