<%--
  Created by IntelliJ IDEA.
  User: Garret
  Date: 03.07.2019
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SHOP</title>
</head>
<body>

<div align="center">
    <form action="/users" method="get">
        Логин <input type="text" name="login"> <br>
        Пароль <input type="password" name="password"> <br>
        <input type="submit" value="Войти">
    </form>

    <form action="/register" method="get">
        <input type="submit" value="Регистрация">
    </form>
</div>

</body>
</html>
