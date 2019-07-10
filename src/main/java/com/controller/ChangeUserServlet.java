package com.controller;

import com.factory.UserServiceFactory;
import com.model.User;
import com.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/change/user")
public class ChangeUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final Logger logger = Logger.getLogger(ChangeUserServlet.class);
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        user = userService.getById(id);
        req.setAttribute("enteredLogin", user.getLogin());
        req.setAttribute("enteredEmail", user.getEmail());
        req.getRequestDispatcher("/change_user.jsp").forward(req, resp);
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
            req.setAttribute("enteredLogin", login);
            req.setAttribute("enteredEmail", email);
            req.getRequestDispatcher("/change_user.jsp").forward(req, resp);
        } else if (!(password.equals(confirmPassword))) {
            req.setAttribute("error", "Passwords not equals!");
            req.setAttribute("enteredLogin", login);
            req.setAttribute("enteredEmail", email);
            req.getRequestDispatcher("/change_user.jsp").forward(req, resp);
        } else {
            String infoMessage = user.toString();
            user.setLogin(login);
            user.setEmail(email);
            user.setPassword(password);
            infoMessage += " was changed to " + user;
            logger.info(infoMessage);
            req.setAttribute("usersList", userService.getAll());
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        }
    }
}
