<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<%
    String error = (String) request.getAttribute("error");
    PrintWriter printWriter = response.getWriter();
    if (error != null) {
        printWriter.write("<div align=\"center\">");
        printWriter.write(error);
        printWriter.write("</div>");
    }
%>
<div align="center">
    <form action="/register" method="post">
        Login <input type="text" name="login" value="<%= (request.getAttribute("enteredLogin") == null) ? ""
                      : request.getAttribute("enteredLogin")%>"> <br>
        E-mail <input type="email" name="email"
                      value="<%= (request.getAttribute("enteredEmail") == null) ? ""
                      : request.getAttribute("enteredEmail")%>"> <br>
        Password <input type="password" name="password"> <br>
        Confirm password <input type="password" name="confirm"> <br>
        <input type="submit" value="Зарегистрироваться">
    </form>

    <button><a href="index.jsp">Вернуться на главную</a></button>

</div>
</body>
</html>
