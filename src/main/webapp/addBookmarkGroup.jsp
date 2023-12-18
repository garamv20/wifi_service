<%--
  Created by IntelliJ IDEA.
  User: garam
  Date: 2023/12/18
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="header.jsp"/>
<body>
    <h1>북마크 그룹 추가</h1>
    <jsp:include page="nav.jsp"/>
    <form action="addBookmarkGroupOk.jsp" method="post" name="form">
        <table>
            <tr>
                <th>북마크 이름</th>
                <td>
                    <input type="text" name="name" value="">
                </td>
            </tr>
            <tr>
                <th>순서</th>
                <td>
                    <input type="number" name="orderNo" value="">
                </td>
            </tr>
        </table>
        <input style="align-content: center" type="button" value="추가" onclick="check_ok()">
    </form>
</body>
<script>
    function check_ok(){
        if(document.form.name.value.length == 0){
            alert("북마크 이름을 입력하세요.");
            form.name.focus();
            return;
        }
        if(document.form.orderNo.value.length == 0){
            alert("순서를 입력하세요.");
            form.orderNo.focus();
            return;
        }
        document.form.submit();
    }
</script>
</html>
