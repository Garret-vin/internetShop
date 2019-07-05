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

        if (password.equals(confirmPassword)) {
            userService.addUser(email, login, password);
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
