<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
<head>
    <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="app.login.title"/></h1>
<form:form method="post" commandName="loginMvcModel">
    <table>
        <tr>
            <td align="right" width="20%">Username:</td>
            <td width="80%">
                <form:input path="username"/>
            </td>
        </tr>

        <tr>
            <td align="right" width="20%">Password:</td>
            <td width="80%">
                <form:input path="password"/>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Execute">
</form:form>
</body>
</html>