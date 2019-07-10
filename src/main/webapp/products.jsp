<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Products</title>
</head>
<body>

<form action="/add" method="get">
    <button type="submit">Добавить товар</button>
</form>

<table border="1">

    <tr>
        <th>Наименование</th>
        <th>Описание</th>
        <th>Цена</th>
    </tr>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td><a href="/change/product?id=${product.id}">Изменить</a></td>
            <td>
                <form action="/products?id=${product.id}" method="post">
                    <input type="submit" value="Удалить">
                </form>
            </td>
        </tr>
    </c:forEach>

</table>

<br>
<button><a href="users">Пользователи</a></button>

</body>
</html>
