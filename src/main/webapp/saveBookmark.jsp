<%@ page import="service.BookmarkService" %><%--
  Created by IntelliJ IDEA.
  User: garam
  Date: 2023/12/18
  Time: 8:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int wifiId = Integer.parseInt(request.getParameter("wifiId"));
    int groupId = Integer.parseInt(request.getParameter("bGroup"));
    String distance = request.getParameter("distance");

    int re = BookmarkService.insertBookmark(wifiId, groupId);
    if(re > 0){
        response.sendRedirect("wifiDetail.jsp?wifiId="+wifiId+"&distance="+distance);
    }else {
%>
<script>
    alert("저장에 실패했습니다.");
    history.go(-1);
</script>
<%
    }
%>
