package com.controller;

import com.factory.ProductServiceFactory;
import com.model.User;
import com.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        req.setAttribute("role", userFromSession.getRole());
        req.setAttribute("productList", productService.getAll());
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
