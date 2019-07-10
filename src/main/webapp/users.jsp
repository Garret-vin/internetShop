<%@ page import="com.factory.UserServiceFactory" %>
<%@ page import="com.model.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>


<div align="left">
    <%
        List<User> userList = UserServiceFactory.getInstance().getAll();
        PrintWriter printWriter = response.getWriter();

        printWriter.write("<button><a href=\"/register\">Добавить пользователя</a></button>");

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
                printWriter.write("<td>" +
                        "<a href = \"/change/user?id=" + user.getId() + "\"> Изменить</a></td>");
                printWriter.write("<td><form action=\"/users?id=" + user.getId() + "\" method=\"post\">" +
                        "<input type=\"submit\" value=\"Удалить\">" +
                        "</form>" +
                        "</td>");
                printWriter.write("</tr>");

            }
        }
        printWriter.write("</table>");
    %>

    <br>
    <button><a href="products">Товары</a></button>
    <button><a href="index.jsp">Выйти</a></button>

</div>
</body>
</html>
