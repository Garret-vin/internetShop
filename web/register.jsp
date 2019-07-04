<%--
  Created by IntelliJ IDEA.
  User: Garret
  Date: 03.07.2019
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<%
    String error = (String) request.getAttribute("error");

    if (error != null) {
        response.getWriter().write(error);
    }
%>
<div align="center">
    <form action="/register" method="post">
        Login <input type="text" name="login" value=${defaultLogin}> <br>
        E-mail <input type="email" name="email" value="${defaultEmail}"> <br>
        Password <input type="password" name="password"> <br>
        Confirm password <input type="password" name="confirm"> <br>
        <input type="submit" value="Зарегистрироваться">
    </form>

    <form action="index.jsp">
        <input type="submit" value="Вернуться на главную">
    </form>
</div>
</body>
</html>
