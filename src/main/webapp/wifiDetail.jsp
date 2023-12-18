<%@ page import="dto.Wifi" %>
<%@ page import="service.WifiService" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.BookmarkGroup" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.BookmarkService" %>
<%--
  Created by IntelliJ IDEA.
  User: garam
  Date: 2023/12/18
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="header.jsp"/>
<body>
    <h1>와이파이 정보 구하기</h1>
    <jsp:include page="nav.jsp"/>
    <%
        int id = 0;
        String distance = "";
        Wifi wifi = null;

        if (request.getParameter("wifiId") != null && request.getParameter("distance") != null) {
            id = Integer.parseInt(request.getParameter("wifiId"));
            distance = request.getParameter("distance");
            wifi = WifiService.selectWifiOne(id, distance);
        }

        List<BookmarkGroup> groupList = new ArrayList<>();
        groupList = BookmarkService.selectBookmarkGroup();
    %>
    <form action="saveBookmark.jsp?wifiId=<%=id%>&distance=<%=distance%>" method="post" name="form">
        <select name="bGroup">
            <option value selected>북마크 그룹 이름 선택</option>
            <%
                if (!groupList.isEmpty()) {
                    for (int i = 0; i < groupList.size(); i++) {
                        BookmarkGroup group = groupList.get(i);
            %>
            <option value=<%=group.getId()%>><%=group.getName()%></option>
            <%
                    }
                }
            %>
        </select>
        <input type="button" onclick="check_ok()" value="북마크 추가하기">
    </form>
    <table>
        <tr>
            <th>거리(Km)</th>
            <td><%=wifi.getDistance()%></td>
        </tr>
        <tr>
            <th>관리번호</th>
            <td><%=wifi.getxSwifiMgrNo()%></td>
        </tr>
        <tr>
            <th>자치구</th>
            <td><%=wifi.getxSwifiWrdofc()%></td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td><%=wifi.getxSwifiMainNm()%></td>
        </tr>
        <tr>
            <th>도로명주소</th>
            <td><%=wifi.getxSwifiAdres1()%></td>
        </tr>
        <tr>
            <th>상세주소</th>
            <td><%=wifi.getxSwifiAdres2()%></td>
        </tr>
        <tr>
            <th>설치위치(층)</th>
            <td><%=wifi.getxSwifiInstlFloor()%></td>
        </tr>
        <tr>
            <th>설치유형</th>
            <td><%=wifi.getxSwifiInstlTy()%></td>
        </tr>
        <tr>
            <th>설치기관</th>
            <td><%=wifi.getxSwifiInstlMby()%></td>
        </tr>
        <tr>
            <th>서비스구분</th>
            <td><%=wifi.getxSwifiSvcSe()%></td>
        </tr>
        <tr>
            <th>망종류</th>
            <td><%=wifi.getxSwifiCmcwr()%></td>
        </tr>
        <tr>
            <th>설치년도</th>
            <td><%=wifi.getxSwifiCnstcYear()%></td>
        </tr>
        <tr>
            <th>실내외구분</th>
            <td><%=wifi.getxSwifiInoutDoor()%></td>
        </tr>
        <tr>
            <th>WIFI접속환경</th>
            <td><%=wifi.getxSwifiRemars3()%></td>
        </tr>
        <tr>
            <th>X좌표</th>
            <td><%=wifi.getLat()%></td>
        </tr>
        <tr>
            <th>Y좌표</th>
            <td><%=wifi.getLnt()%></td>
        </tr>
        <tr>
            <th>작업일자</th>
            <td><%=wifi.getWorkDttm()%></td>
        </tr>
    </table>
<script>
    function check_ok(){
        if(document.form.bGroup.value.length == 0){
            alert("북마크 그룹을 선택해 주세요");
            form.bGroup.focus();
            return;
        }
        document.form.submit();
    }
</script>
</body>
</html>
