<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Area privada</h1>

<p>
<table>
    <tr>
        <td>Usuario:</td>
        <td><c:out value="${user.firstName}"></c:out> <c:out value="${user.lastName}"></c:out></td>
    </tr>

    <tr>
        <td>Role:</td>
        <td><c:out value="${user.role}"></c:out></td>
    </tr>
</table>

</p>

</body>
</html>
