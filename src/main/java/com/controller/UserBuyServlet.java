package com.controller;

import com.factory.BasketServiceFactory;
import com.factory.ProductServiceFactory;
import com.model.Basket;
import com.model.Product;
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

@WebServlet("/user/buy")
public class UserBuyServlet extends HttpServlet {

    private static final BasketService basketService = BasketServiceFactory.getInstance();
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");

        Product product = null;
        Optional<Product> optionalProduct = productService.getById(productId);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        }

        Basket basket = null;
        Optional<Basket> optionalBasket = basketService.getBasketByUser(user);
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
        }

        basketService.addProduct(basket, product);
        resp.sendRedirect("/user/products");
    }
}
