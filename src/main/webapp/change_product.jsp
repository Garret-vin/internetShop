<%--
  Created by IntelliJ IDEA.
  User: Garret
  Date: 10.07.2019
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change product</title>
</head>
<body>

<form action="/change/product" method="post">
    Название <input type="text" name="name" value="<%= (request.getAttribute("oldName") == null) ? ""
                      : request.getAttribute("oldName")%>"> <br>
    Описание <input type="text" name="description" value="<%= (request.getAttribute("oldDescription") == null) ? ""
                      : request.getAttribute("oldDescription")%>"> <br>
    Цена <input type="number" step="0.01" min="0" placeholder="0,00" name="price"
                value="<%= (request.getAttribute("oldPrice") == null) ? 0.00
                      : request.getAttribute("oldPrice")%>"> <br>
    <input type="submit" value="Изменить товар"></form>
</form>

<button><a href="/products.jsp">Вернуться</a></button>
</body>
</html>
