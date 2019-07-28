package com.controller;

import com.factory.UserServiceFactory;
import com.model.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/admin/add/user")
public class AddUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm");
        String role = req.getParameter("role");
        Map<String, String> loginToEmailMap = userService.getMapLoginToEmail();

        if (email.isEmpty() || login.isEmpty() || password.isEmpty() || role == null) {
            req.setAttribute("error", "Empty fields!");
            req.setAttribute("enteredLogin", login);
            req.setAttribute("enteredEmail", email);
            req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
        } else if (loginToEmailMap.containsKey(login)
                || loginToEmailMap.containsValue(email)) {
            req.setAttribute("enteredLogin", login);
            req.setAttribute("enteredEmail", email);
            req.setAttribute("error", "Пользователь с таким логином или " +
                    "электронной почтой уже зарегистрирован!");
            req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
        } else if (password.equals(confirmPassword)) {
            User user = new User(login, email, password, role);
            userService.add(user);
            resp.sendRedirect("/admin/users");
        } else {
            req.setAttribute("error", "Passwords not equals!");
            req.setAttribute("enteredLogin", login);
            req.setAttribute("enteredEmail", email);
            req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
        }
    }
}
