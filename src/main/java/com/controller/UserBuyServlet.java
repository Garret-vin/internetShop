package com.controller;

import com.factory.BasketServiceFactory;
import com.model.Basket;
import com.model.User;
import com.service.BasketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/buy")
public class UserBuyServlet extends HttpServlet {

    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("id"));
        Long userId = ((User) req.getSession().getAttribute("user")).getId();
        Optional<Basket> optionalBasket = basketService.getBasketByUserId(userId);
        optionalBasket.ifPresent(basket -> basketService.addProduct(basket.getId(), productId));
        resp.sendRedirect("/user/products");
    }
}
