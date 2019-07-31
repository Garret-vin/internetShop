package com.controller;

import com.factory.BasketServiceFactory;
import com.factory.OrderServiceFactory;
import com.model.Basket;
import com.model.Order;
import com.model.User;
import com.service.BasketService;
import com.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/payment/confirm")
public class PaymentConfirmServlet extends HttpServlet {

    private static final OrderService orderService = OrderServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/payment_confirm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String confirm = req.getParameter("confirm");
        User user = (User) req.getSession().getAttribute("user");

        Optional<Order> optionalOrder = orderService.getLastOrderForUser(user);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getCode().getValue().equals(confirm)) {
                Basket basket = new Basket(order.getUser());
                basketService.add(basket);
                req.setAttribute("message", "Покупка успешно совершена!");
            } else {
                req.setAttribute("message", "Неверный код. Введите заново!");
            }
        }
        req.getRequestDispatcher("/payment_confirm.jsp").forward(req, resp);
    }
}
