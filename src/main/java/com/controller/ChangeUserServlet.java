package com.controller;

import com.factory.UserServiceFactory;
import com.model.User;
import com.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/change/user")
public class ChangeUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final Logger logger = Logger.getLogger(ChangeUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Optional<User> optionalUser = userService.getById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            req.setAttribute("userId", id);
            req.setAttribute("enteredLogin", user.getLogin());
            req.setAttribute("enteredEmail", user.getEmail());
            req.getRequestDispatcher("/change_user.jsp").forward(req, resp);
        } else {
            logger.error("Error: Can't edit user. Reason: user not found!");
            resp.sendRedirect("/admin/users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.valueOf(req.getParameter("id"));
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm");
        String role = req.getParameter("role");

        if (email.isEmpty() || login.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Empty fields!");
            req.setAttribute("enteredLogin", login);
            req.setAttribute("enteredEmail", email);
            req.setAttribute("enteredPassword", password);
            req.setAttribute("enteredConfirm", confirmPassword);
            req.getRequestDispatcher("/change_user.jsp").forward(req, resp);
        } else if (!(password.equals(confirmPassword))) {
            req.setAttribute("error", "Passwords not equals!");
            req.setAttribute("enteredLogin", login);
            req.setAttribute("enteredEmail", email);
            req.getRequestDispatcher("/change_user.jsp").forward(req, resp);
        } else {
            String encryptedPassword = DigestUtils.sha256Hex(password);
            User user = new User(id, login, email, encryptedPassword, role);
            userService.update(user);
            resp.sendRedirect("/admin/users");
        }
    }
}
