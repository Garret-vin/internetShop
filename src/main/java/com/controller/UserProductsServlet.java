package com.controller;

import com.factory.ProductServiceFactory;
import com.model.Basket;
import com.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/products")
public class UserProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Basket basket = (Basket) req.getSession().getAttribute("basket");
        req.setAttribute("size", basket.getSize());
        req.setAttribute("productList", productService.getAll());
        req.getRequestDispatcher("/products_user.jsp").forward(req, resp);
    }
}
