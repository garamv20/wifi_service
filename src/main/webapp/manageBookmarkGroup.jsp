<%@ page import="dto.BookmarkGroup" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="service.BookmarkService" %><%--
  Created by IntelliJ IDEA.
  User: garam
  Date: 2023/12/18
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <jsp:include page="header.jsp"/>
<body>
  <%
    List<BookmarkGroup> list = new ArrayList<>();
    list = BookmarkService.selectBookmarkGroup();
  %>
  <h1>북마크 그룹</h1>
  <jsp:include page="nav.jsp"/>
  <button onclick="location.href = 'addBookmarkGroup.jsp'">북마크 그룹 이름 추가</button>
  <table>
    <tr>
      <th>ID</th>
      <th>북마크 이름</th>
      <th>순서</th>
      <th>등록일자</th>
      <th>수정일자</th>
      <th>비고</th>
    </tr>
    <%
      if (!list.isEmpty()){
        for (int i = 0; i < list.size(); i++) {
          BookmarkGroup group = list.get(i);
          int id = group.getId();
          String name = group.getName();
          int orderNo = group.getOrderNo();
    %>
    <tr>
      <td><%=id%></td>
      <td><%=name%></td>
      <td><%=orderNo%></td>
      <td><%=group.getCreateDttm()%></td>
      <td><%=group.getUpdateDttm()%></td>
      <td><a href="updateBookmarkGroup.jsp?id=<%=id%>&name=<%=name%>&orderNo=<%=orderNo%>">수정</a> <a href="javascript:void(0)" onclick="groupDelete(<%=id%>)">삭제</a></td>
    </tr>
    <%
      }
    }else {
    %>
    <tr>
      <td colspan="6">등록된 북마크그룹이 없습니다.</td>
    </tr>
    <%
      }
    %>
  </table>
<script>
  function groupDelete(id){
    if(confirm("ID:" + id + " 북마크그룹을 삭제하시겠습니까?")){
      location.href = "deleteBookmarkGroup.jsp?id="+id;
    }
  }
</script>
</body>
</html>
