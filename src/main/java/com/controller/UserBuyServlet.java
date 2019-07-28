package com.controller;

import com.factory.BasketServiceFactory;
import com.model.User;
import com.service.BasketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/buy")
public class UserBuyServlet extends HttpServlet {

    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("id"));
        Long userId = ((User) req.getSession().getAttribute("user")).getId();

        basketService.addProduct(userId, productId);
        resp.sendRedirect("/user/products");
    }
}
