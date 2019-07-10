<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>


<div align="center">

    ${error}

    <form action="/register" method="post">
        Login <input type="text" name="login" value="${enteredLogin}"> <br>
        E-mail <input type="email" name="email" value="${enteredEmail}"> <br>
        Password <input type="password" name="password"> <br>
        Confirm password <input type="password" name="confirm"> <br>
        <input type="submit" value="Зарегистрироваться">
    </form>

    <button><a href="/index.jsp">Выйти</a></button>

</div>
</body>
</html>
