<%@ page import="com.factory.UserDaoFactory" %>
<%@ page import="com.model.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
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
    <button><a href="products.jsp">Товары</a></button>
    <button><a href="index.jsp">Вернуться на главную</a></button>

</div>
</body>
</html>
