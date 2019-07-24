package com.controller;

import com.factory.BasketServiceFactory;
import com.factory.ProductServiceFactory;
import com.model.Basket;
import com.model.User;
import com.service.BasketService;
import com.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/products")
public class UserProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = ((User) req.getSession().getAttribute("user")).getId();
        Optional<Basket> optionalBasket = basketService.getBasketByUserId(userId);
        optionalBasket.ifPresent(basket ->
                req.setAttribute("size", basketService.size(basket.getId())));
        req.setAttribute("productList", productService.getAll());
        req.getRequestDispatcher("/products_user.jsp").forward(req, resp);
    }
}
