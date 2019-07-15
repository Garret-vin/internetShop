package com.controller;

import com.factory.ProductServiceFactory;
import com.model.Product;
import com.model.User;
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
        Long id = Long.valueOf(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");
        Optional<Product> optionalProduct = productService.getById(id);
        optionalProduct.ifPresent(user::addProductToBasket);
        resp.sendRedirect("/user/products");
    }
}
