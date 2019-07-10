package com.controller;

import com.factory.UserServiceFactory;
import com.model.User;
import com.service.UserService;
import com.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (SessionUtil.isAuthorized) {
            req.setAttribute("usersList", userService.getAll());
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        } else {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            User registeredUser = userService.getByLogin(login);
            if ((registeredUser != null) && (registeredUser.getPassword().equals(password))) {
                req.setAttribute("usersList", userService.getAll());
                SessionUtil.isAuthorized = true;
                req.getRequestDispatcher("users.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Пользователь с таким логином и паролем не найден,\n" +
                        " пожалуйста сначала зарегистрируйтесь!");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Long id = Long.valueOf(req.getParameter("id"));
        userService.removeUser(userService.getById(id));
        req.setAttribute("usersList", userService.getAll());
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}
