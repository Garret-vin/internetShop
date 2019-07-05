<%@ page import="factory.ProductServiceFactory" %>
<%@ page import="model.Product" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Garret
  Date: 05.07.2019
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<%
    List<Product> productList = ProductServiceFactory.getInstance().getAll();
    PrintWriter printWriter = response.getWriter();
    printWriter.write("<form action=\"/add\" method=\"get\">\n" +
            "    <button type=\"submit\">Добавить товар</button>\n" +
            "</form>");

    printWriter.write("<table border=\"1\">");
    printWriter.write("<tr>");
    printWriter.write("<th>Наименование</th>");
    printWriter.write("<th>Описание</th>");
    printWriter.write("<th>Цена</th>");
    printWriter.write("</tr>");
    if (!productList.isEmpty()) {
        for (Product product : productList) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + product.getName() + "</td>");
            printWriter.write("<td>" + product.getDescription() + "</td>");
            printWriter.write("<td>" + product.getPrice() + "</td>");
            printWriter.write("</tr>");
        }
    }
    printWriter.write("</table>");
%>

<br>
<form action="users.jsp">
    <input type="submit" value="Пользователи">
</form>
</body>
</html>
