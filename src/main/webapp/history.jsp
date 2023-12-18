<%@ page import="java.util.List" %>
<%@ page import="dto.History" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.HistoryService" %>
<%--
  Created by IntelliJ IDEA.
  User: garam
  Date: 2023/12/18
  Time: 1:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"/>
<body>
<%
  if (request.getParameter("ID") != null){
     boolean isSuccess = HistoryService.deleteHistory(request.getParameter("ID"));
     if (isSuccess){
       System.out.println("삭제 성공");
     }
  }

  List<History> historyList = new ArrayList<>();
  historyList = HistoryService.selectHistory(); // 히스토리 조회
  int id;
%>
<h1>위치 히스토리 목록</h1>
<jsp:include page="nav.jsp"/>
<div>
  <table>
    <tr>
      <th>ID</th>
      <th>X좌표</th>
      <th>Y좌표</th>
      <th>조회일자</th>
      <th>비고</th>
    </tr>
    <%
      if (!historyList.isEmpty()){
        for (int i = 0; i < historyList.size(); i++) {
          History history = historyList.get(i);
          id = history.getId();
    %>
    <tr>
      <td><%=id%></td>
      <td><%=history.getxCoord()%></td>
      <td><%=history.getyCoord()%></td>
      <td><%=history.getDttm()%></td>
      <td><input type="button" value="삭제" onclick="deleteHistory(<%=id%>)"></td>
    </tr>
    <%
      }
    }else {
    %>
    <tr>
      <td colspan="5">저장된 위치 히스토리가 없습니다.</td>
    </tr>
    <%
      }
    %>
  </table>
</div>
<script>
  function deleteHistory(id){
    if(confirm("ID:" + id + " 히스토리를 삭제하시겠습니까?")){
      $.ajax({
        url: "http://localhost:8080/history.jsp",
        data: {ID: id},
        success: function (){
          location.reload();
        }
      });
    }
  }
</script>
</body>
</html>
