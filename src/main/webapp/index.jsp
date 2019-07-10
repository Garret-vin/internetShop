<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SHOP</title>
</head>
<body>

<div align="center">

    ${error}

    <form action="/users" method="get">
        Логин <input type="text" name="login"> <br>
        Пароль <input type="password" name="password"> <br>
        <input type="submit" value="Войти">
    </form>

    <button><a href="/register">Зарегистрироваться</a></button>
</div>

</body>
</html>
