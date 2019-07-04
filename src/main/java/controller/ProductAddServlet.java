package controller;

import dao.DaoInterface;
import dao.ProductDao;
import model.Product;
import service.ProductDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add")
public class ProductAddServlet extends HttpServlet {

    private DaoInterface<Product> productDao = ProductDaoFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("productAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));

        Product product = ProductDao.create(name, description, price);

        productDao.add(product);
        resp.setStatus(HttpServletResponse.SC_OK);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}
