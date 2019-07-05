<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SHOP</title>
</head>
<body>

<form action="/add" method="post">
    Название <input type="text" name="name"> <br>
    Описание <input type="text" name="description"> <br>
    Цена <input type="number" step="0.01" min="0" placeholder="0,00" name="price"> <br>
    <input type="submit" value="Добавить товар"></form>
</form>
<form action="index.jsp">
    <input type="submit" value="Вернуться на главную">
</form>

</body>
</html>
