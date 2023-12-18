<%@ page import="dto.Bookmark" %>
<%@ page import="java.util.*" %>
<%@ page import="service.BookmarkService" %><%--
  Created by IntelliJ IDEA.
  User: garam
  Date: 2023/12/18
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"/>
<body>
<%
  List<Bookmark> list = new ArrayList<>();
  list = BookmarkService.selectBookmark();
%>
<h1>북마크 목록</h1>
<jsp:include page="nav.jsp"/>
<button onclick="location.href = 'addBookmarkGroup.jsp'">북마크 그룹 이름 추가</button>
  <table>
    <tr>
      <th>ID</th>
      <th>북마크 이름</th>
      <th>와이파이명</th>
      <th>등록일자</th>
      <th>비고</th>
    </tr>
    <%
      if (!list.isEmpty()){
        for (int i = 0; i < list.size(); i++) {
          Bookmark bookmark = list.get(i);
          int id = bookmark.getId();
    %>
    <tr>
      <td><%=id%></td>
      <td><%=bookmark.getGroupName()%></td>
      <td><%=bookmark.getWifiName()%></td>
      <td><%=bookmark.getDttm()%></td>
      <td><a href="javascript:void(0)" onclick="bookmarkDelete(<%=id%>)">삭제</a></td>
    </tr>
    <%
      }
    }else {
    %>
    <tr>
      <td colspan="5">등록된 북마크가 없습니다.</td>
    </tr>
    <%
      }
    %>
  </table>
<script>
  function bookmarkDelete(id){
    if(confirm("ID:" + id + " 북마크를 삭제하시겠습니까?")){
      location.href = "deleteBookmark.jsp?id="+id;
    }
  }
</script>
</body>
</html>
