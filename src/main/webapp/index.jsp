<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SHOP</title>
</head>
<body>

<div align="center">

    <%
        String error = (String) request.getAttribute("error");
        PrintWriter printWriter = response.getWriter();

        if (error != null) {
            printWriter.write("<div align=\"center\">");
            printWriter.write(error);
            printWriter.write("</div>");
        }
    %>

    <form action="/users" method="get">
        Логин <input type="text" name="login"> <br>
        Пароль <input type="password" name="password"> <br>
        <input type="submit" value="Войти">
    </form>

    <button><a href="/register">Зарегистрироваться</a></button>
</div>

</body>
</html>
