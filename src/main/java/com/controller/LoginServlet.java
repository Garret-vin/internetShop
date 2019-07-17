package com.controller;

import com.factory.UserServiceFactory;
import com.model.Basket;
import com.model.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User registeredUser = null;
        Optional<User> optionalUser = userService.getByLogin(login);
        if (optionalUser.isPresent()) {
            registeredUser = optionalUser.get();
        }

        if (registeredUser != null && registeredUser.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", registeredUser);
            if ("admin".equals(registeredUser.getRole())) {
                resp.sendRedirect("/admin/users");
            } else {
                Basket basket = new Basket(registeredUser);
                session.setAttribute("basket", basket);
                resp.sendRedirect("/user/products");
            }
        } else {
            req.setAttribute("error", "Пользователь с таким логином и паролем не найден");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
