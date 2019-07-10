<%@ page import="com.factory.ProductServiceFactory" %>
<%@ page import="com.model.Product" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
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
            printWriter.write("<td>" +
                    "<a href = \"/change/product?id=" + product.getId() + "\"> Изменить</a></td>");
            printWriter.write("<td><form action=\"/products?id=" + product.getId() + "\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Удалить\">" +
                    "</form></td>");
            printWriter.write("</tr>");
        }
    }
    printWriter.write("</table>");
%>

<br>

<button><a href="users.jsp">Пользователи</a></button>

</body>
</html>
