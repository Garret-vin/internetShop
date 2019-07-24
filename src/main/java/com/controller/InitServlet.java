/*
package com.controller;

import com.factory.UserServiceFactory;
import com.model.User;
import com.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        String passwordTest = DigestUtils.sha256Hex("test");
        String passwordUser = DigestUtils.sha256Hex("user");
        User admin = new User("test", "test@test", passwordTest, "admin");
        User user = new User("user", "garret.ork@gmail.com", passwordUser, "user");
        userService.add(admin);
        userService.add(user);
    }
}
*/
