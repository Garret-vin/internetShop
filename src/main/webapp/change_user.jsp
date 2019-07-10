<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change User</title>
</head>
<body>
<%
    String error = (String) request.getAttribute("error");
    PrintWriter printWriter = response.getWriter();
    if (error != null) {
        printWriter.write(error);
    }
%>
<form action="/change/user" method="post">
    Login <input type="text" name="login" value="<%= (request.getAttribute("enteredLogin") == null) ? ""
                      : request.getAttribute("enteredLogin")%>"> <br>
    E-mail <input type="email" name="email"
                  value="<%= (request.getAttribute("enteredEmail") == null) ? ""
                      : request.getAttribute("enteredEmail")%>"> <br>
    Password <input type="password" name="password"> <br>
    Confirm password <input type="password" name="confirm"> <br>
    <input type="submit" value="Подтвердить">
</form>

<button><a href="/users.jsp">Вернуться</a></button>

</body>
</html>
