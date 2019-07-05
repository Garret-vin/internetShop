<%@ page import="factory.UserDaoFactory" %>
<%@ page import="model.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Garret
  Date: 04.07.2019
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<div align="left">
    <%
        List<User> userList = UserDaoFactory.getInstance().getAll();
        PrintWriter printWriter = response.getWriter();

        printWriter.write("<form action=\"/register\" method=\"get\">\n" +
                "<input type=\"submit\" value=\"Добавить пользователя\">\n" +
                "</form>");

        printWriter.write("<table border=\"1\">");
        printWriter.write("<tr>");
        printWriter.write("<th>Email</th>");
        printWriter.write("<th>Login</th>");
        printWriter.write("<th>Password</th>");
        printWriter.write("</tr>");
        if (!userList.isEmpty()) {
            for (User user : userList) {
                printWriter.write("<tr>");
                printWriter.write("<td>" + user.getEmail() + "</td>");
                printWriter.write("<td>" + user.getLogin() + "</td>");
                printWriter.write("<td>" + user.getPassword() + "</td>");
                printWriter.write("</tr>");
            }
        }
        printWriter.write("</table>");
    %>

    <br>
    <form action="/products" method="get">
        <input type="submit" value="Товары">
    </form>

    <br>
    <form action="index.jsp">
        <input type="submit" value="Выйти">
    </form>

</div>
</body>
</html>
