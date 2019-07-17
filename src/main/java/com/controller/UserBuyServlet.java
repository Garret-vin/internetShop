package com.controller;

import com.factory.ProductServiceFactory;
import com.model.Basket;
import com.model.Product;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("id"));
        Basket basket = (Basket) req.getSession().getAttribute("basket");

        Optional<Product> optionalProduct = productService.getById(productId);
        optionalProduct.ifPresent(basket::addProduct);

        resp.sendRedirect("/user/products");
    }
}
