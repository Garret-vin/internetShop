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
import java.util.stream.Collectors;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Map<String, String> userMap = getMapLoginToPassword();
        if (userMap.containsKey(login)
                && userMap.get(login).equals(password)) {
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Пользователь с таким логином и паролем не найден,\n" +
                    " пожалуйста сначала зарегистрируйтесь!");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private Map<String, String> getMapLoginToPassword() {
        return userService.getAll().stream()
                .collect(Collectors.toMap(User::getLogin, User::getPassword));
    }
}
