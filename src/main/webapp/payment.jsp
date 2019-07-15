<%--
  Created by IntelliJ IDEA.
  User: Garret
  Date: 15.07.2019
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оплата товара</title>
</head>
<body>
<h1>Создание заказа</h1>
<form action="/payment" method="post">
    E-mail <input type="text" name="email" value="${userEmail}"><br>
    Номер телефона <input type="tel" value="+123456789012"><br>
    Адрес доставки <input type="text" name="address"><br>
    <input type="submit" value="Подтвердить заказ">
</form>
</body>
</html>
