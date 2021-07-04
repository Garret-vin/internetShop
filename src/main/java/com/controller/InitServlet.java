package com.controller;

import com.factory.BasketServiceFactory;
import com.factory.ProductServiceFactory;
import com.factory.UserServiceFactory;
import com.model.Basket;
import com.model.Product;
import com.model.User;
import com.service.BasketService;
import com.service.ProductService;
import com.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        String passwordTest = DigestUtils.sha256Hex("test");
        String passwordUser = DigestUtils.sha256Hex("user");
        User admin = new User("test", "test@test", passwordTest, "admin");
        User user = new User("user", "garret.ork@gmail.com", passwordUser, "user");
        userService.add(admin);
        userService.add(user);

        Basket basket = new Basket(user);
        basketService.add(basket);

        Product product = new Product("baton", "black", 12.34);
        productService.add(product);
        Product product2 = new Product("bread", "white", 42.33);
        productService.add(product2);
        Product product3 = new Product("milk", "cows", 20.90);
        productService.add(product3);
    }
}
