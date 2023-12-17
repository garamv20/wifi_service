<%@ page import="service.ApiService" %><%--
  Created by IntelliJ IDEA.
  User: garam
  Date: 2023/12/17
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <%
        int totalCnt = ApiService.getApiInfoCnt();
        int saveCnt = ApiService.insertWifi(totalCnt);
    %>
    <div style="align-content: center">
        <h1><%=totalCnt%>개 와이파이 정보 중 <%=saveCnt%> 개의 와이파이 정보를 정상적으로 저장하였습니다.</h1>
        <a href="index.jsp">위치 히스토리 목록</a>
    </div>
</body>
</html>
