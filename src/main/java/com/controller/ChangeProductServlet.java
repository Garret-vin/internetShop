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

@WebServlet("/admin/change/product")
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
        } else {
            logger.error("Error: Can't edit product. Reason: product not found!");
            resp.sendRedirect("/admin/products");
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
        if (enteredPrice != null && !enteredPrice.isEmpty()) {
            price = Double.valueOf(enteredPrice);
        }

        Long id = Long.valueOf(req.getParameter("id"));
        Product oldProduct;
        Optional<Product> optionalProduct = productService.getById(id);
        if (optionalProduct.isPresent()) {
            oldProduct = optionalProduct.get();
            if (name.isEmpty() || description.isEmpty()) {
                req.setAttribute("error", "Empty fields!");
                req.setAttribute("oldName", name);
                req.setAttribute("oldDescription", description);
                req.setAttribute("oldPrice", price);
                req.getRequestDispatcher("/change_product.jsp").forward(req, resp);
            } else {
                Product newProduct = new Product(name, description, price);
                productService.update(oldProduct, newProduct);
                resp.sendRedirect("/admin/products");
            }
        }
    }
}
