package com.controller;

import com.factory.BasketServiceFactory;
import com.factory.UserServiceFactory;
import com.model.Basket;
import com.model.User;
import com.service.BasketService;
import com.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/add/user")
public class AddUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();

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
        Optional<User> optionalPresentUser = userService.getByLoginOrEmail(login, email);

        if (email.isEmpty() || login.isEmpty() || password.isEmpty() || role == null) {
            req.setAttribute("error", "Empty fields!");
            req.setAttribute("enteredLogin", login);
            req.setAttribute("enteredEmail", email);
            req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
        } else if (optionalPresentUser.isPresent()) {
            req.setAttribute("enteredLogin", login);
            req.setAttribute("enteredEmail", email);
            req.setAttribute("error", "Пользователь с таким логином или " +
                    "электронной почтой уже зарегистрирован!");
            req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
        } else if (!password.equals(confirmPassword) || password.length() > 16) {
            req.setAttribute("error", "Passwords not equals or too long!");
            req.setAttribute("enteredLogin", login);
            req.setAttribute("enteredEmail", email);
            req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
        } else {
            String encryptedPassword = DigestUtils.sha256Hex(password);
            User user = new User(login, email, encryptedPassword, role);
            userService.add(user);

            Optional<User> optionalNewUser = userService.getByLogin(login);
            if (optionalNewUser.isPresent()) {
                Basket basket = new Basket(optionalNewUser.get());
                basketService.add(basket);
            }
            resp.sendRedirect("/admin/users");
        }
    }
}
