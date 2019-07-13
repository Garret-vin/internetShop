package com.controller;

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
import java.util.Optional;

@WebServlet("/change/product")
public class ChangeProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();
    private static final Logger logger = Logger.getLogger(ChangeProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Optional<Product> optionalProduct = productService.getById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            req.setAttribute("productId", id);
            req.setAttribute("oldName", product.getName());
            req.setAttribute("oldDescription", product.getDescription());
            req.setAttribute("oldPrice", product.getPrice());
            req.getRequestDispatcher("/change_product.jsp").forward(req, resp);
        }
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

        Long id = Long.valueOf(req.getParameter("id"));
        Product product;
        Optional<Product> optionalProduct = productService.getById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
            if (name.isEmpty() || description.isEmpty()) {
                req.setAttribute("error", "Empty fields!");
                req.setAttribute("oldName", product.getName());
                req.setAttribute("oldDescription", product.getDescription());
                req.setAttribute("oldPrice", product.getPrice());
                req.getRequestDispatcher("/change_product.jsp").forward(req, resp);
            } else {
                String infoMessage = product.toString();
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                infoMessage += " was changed to " + product;
                logger.info(infoMessage);
                resp.sendRedirect("/products");
            }
        }
    }
}
