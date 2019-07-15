package com.controller;

import com.factory.ProductServiceFactory;
import com.model.Product;
import com.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add/product")
public class AddProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String enteredPrice = req.getParameter("price");
        Double price = 0.0;
        if (enteredPrice != null && !enteredPrice.equals("")) {
            price = Double.valueOf(enteredPrice);
        }

        Product product = new Product(name, description, price);
        productService.addProduct(product);

        req.setAttribute("productList", productService.getAll());
        req.getRequestDispatcher("/products_admin.jsp").forward(req, resp);
    }
}
