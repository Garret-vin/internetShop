package com.controller;

import com.factory.BasketServiceFactory;
import com.factory.CodeServiceFactory;
import com.factory.MailServiceFactory;
import com.factory.OrderServiceFactory;
import com.model.Basket;
import com.model.Code;
import com.model.Order;
import com.model.User;
import com.service.BasketService;
import com.service.CodeService;
import com.service.MailService;
import com.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    private static final MailService mailService = MailServiceFactory.getInstance();
    private static final OrderService orderService = OrderServiceFactory.getInstance();
    private static final CodeService codeService = CodeServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("userEmail", user.getEmail());
        req.getRequestDispatcher("/payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String phoneNumber = req.getParameter("phone");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        codeService.add(new Code(user));

        Code code = null;
        Optional<Code> optionalCode = codeService.getLastCodeForUser(user);
        if (optionalCode.isPresent()) {
            code = optionalCode.get();
        }

        Basket basket = null;
        Optional<Basket> optionalBasket = basketService.getBasketByUser(user);
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
        }

        Order order = new Order(basket, user, code, email, phoneNumber, address);
        orderService.add(order);
        new Thread(() -> mailService.sendConfirmCode(order)).start();

        resp.sendRedirect("/payment/confirm");
    }
}
