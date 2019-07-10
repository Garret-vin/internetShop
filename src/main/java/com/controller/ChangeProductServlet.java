package com.controller;

import com.dao.impl.ProductDaoImpl;
import com.factory.ProductServiceFactory;
import com.model.Product;
import com.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/change/product")
public class ChangeProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();
    private static final Logger logger = Logger.getLogger(ChangeProductServlet.class);
    Product product;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        product = productService.getById(id);
        req.setAttribute("oldName", product.getName());
        req.setAttribute("oldDescription", product.getDescription());
        req.setAttribute("oldPrice", product.getPrice());
        req.getRequestDispatcher("/change_product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));

        String infoMessage = product.toString();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        infoMessage += " was changed to " + product;
        logger.info(infoMessage);

        resp.setStatus(HttpServletResponse.SC_OK);
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
