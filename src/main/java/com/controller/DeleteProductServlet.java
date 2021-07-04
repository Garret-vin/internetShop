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
import java.util.Optional;

@WebServlet("/admin/delete/product")
public class DeleteProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Optional<Product> optionalProduct = productService.getById(id);
        optionalProduct.ifPresent(productService::remove);
        resp.sendRedirect("/admin/products");
    }
}
