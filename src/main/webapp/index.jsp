<%@ page import="service.WifiService" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.Wifi" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style/style.css">
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    double lat = request.getParameter("lat") == "" || request.getParameter("lat") == null ? 0.0 : Double.parseDouble(request.getParameter("lat"));
    double lnt = request.getParameter("lnt") == "" || request.getParameter("lnt") == null ? 0.0 : Double.parseDouble(request.getParameter("lnt"));

    List<Wifi> wifiList = new ArrayList<>();

    if (lat > 0 || lnt > 0) {
        wifiList = WifiService.searchNearWifi(lat, lnt);
    }
%>
<h1>와이파이 정보 구하기</h1>
<nav>
    <a href="index.jsp">홈</a> |
    <a href="/css/">위치 히스토리 목록</a> |
    <a href="getApiInfo.jsp">Open API 와이파이 정보 가져오기</a>
</nav>
<br/>
    <div>
        LAT: <input type="number" id="lat" name="lat" value="<%=lat%>">,
        LNT: <input type="number" id="lnt" name="lnt" value="<%=lnt%>">
        <button type="button" onclick="getCurrentPosition()">내 위치 가져오기</button>
        <button type="button" onclick="showNearWifi()">근처 WIFI정보 보기</button>
    </div>
<br/>
<div>
    <table>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        <%
            if (wifiList.size() > 0){
                for (int i = 0; i < wifiList.size(); i++) {
                    Wifi wifi = wifiList.get(i);
        %>
        <tr>
            <td><%=wifi.getDistance()%></td>
            <td><%=wifi.getxSwifiMgrNo()%></td>
            <td><%=wifi.getxSwifiWrdofc()%></td>
            <td><%=wifi.getxSwifiMainNm()%></td>
            <td><%=wifi.getxSwifiAdres1()%></td>
            <td><%=wifi.getxSwifiAdres2()%></td>
            <td><%=wifi.getxSwifiInstlFloor()%></td>
            <td><%=wifi.getxSwifiInstlTy()%></td>
            <td><%=wifi.getxSwifiInstlMby()%></td>
            <td><%=wifi.getxSwifiSvcSe()%></td>
            <td><%=wifi.getxSwifiCmcwr()%></td>
            <td><%=wifi.getxSwifiCnstcYear()%></td>
            <td><%=wifi.getxSwifiInoutDoor()%></td>
            <td><%=wifi.getxSwifiRemars3()%></td>
            <td><%=wifi.getLat()%></td>
            <td><%=wifi.getLnt()%></td>
            <td><%=wifi.getWorkDttm()%></td>
        </tr>
        <%
                }
            }else {
        %>
        <tr>
            <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
        </tr>
        <%
            }
        %>

    </table>
</div>
    <script>
        // 내위치 가져오기
        function getCurrentPosition(){
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function showPosition(position){
                    // 위도
                    const lat = position.coords.latitude;
                    // 경도
                    const lnt = position.coords.longitude;

                    document.getElementById("lat").value = lat;
                    document.getElementById("lnt").value = lnt;
                });
            } else {
                alert('Geolocation Error');
            }
        }

        function showNearWifi(){
            let lat = document.getElementById("lat").value;
            let lnt = document.getElementById("lnt").value;

            if (lat != "" && lnt != "" && (lat > 0 || lnt > 0)) {
                location.href = "index.jsp?lat=" + lat + "&lnt=" + lnt;
            }else {
                alert("위치 입력 또는 내위치 가져오기 후 조회해 주세요.");
            }
        }
    </script>
</body>
</html>