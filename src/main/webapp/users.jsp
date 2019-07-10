<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>


<div align="left">

    <button><a href="/register">Добавить пользователя</a></button>

    <table border="1">
        <tr>
            <td>Email</td>
            <td>Login</td>
            <td>Password</td>
        </tr>
        <c:forEach var="user" items="${usersList}">
            <tr>
                <td>${user.email}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td><a href="/change/user?id=${user.id}">Изменить</a></td>
                <td>
                    <form action="/users?id=${user.id}" method="post">
                        <input type="submit" value="Удалить">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <button><a href="/products">Товары</a></button>
    <button><a href="index">Выйти</a></button>

</div>
</body>
</html>
