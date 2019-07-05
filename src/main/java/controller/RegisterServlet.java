package controller;

import factory.UserServiceFactory;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm");

        if (email.isEmpty() || login.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Empty fields!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else if (password.equals(confirmPassword)) {
            User user = userService.create(email, login, password);
            userService.add(user);
            resp.setStatus(HttpServletResponse.SC_OK);
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Passwords not equals!");
            req.setAttribute("defaultLogin", login);
            req.setAttribute("defaultEmail", email);
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
