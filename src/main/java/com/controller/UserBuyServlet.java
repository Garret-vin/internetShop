package com.controller;

import com.factory.BasketServiceFactory;
import com.factory.ProductServiceFactory;
import com.model.Basket;
import com.model.Product;
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

    private static final ProductService productService = ProductServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("id"));
        Basket basket = (Basket) req.getSession().getAttribute("basket");

        Product product = null;
        Optional<Product> optionalProduct = productService.getById(productId);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        }

        basketService.addProduct(basket, product);
        resp.sendRedirect("/user/products");
    }
}
