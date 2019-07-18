package com.controller;

import com.factory.OrderServiceFactory;
import com.model.Order;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/payment_confirm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = (Long) req.getSession().getAttribute("orderId");
        String confirm = req.getParameter("confirm");

        Order order = null;
        Optional<Order> optionalOrder = orderService.getById(orderId);
        if (optionalOrder.isPresent()) {
            order = optionalOrder.get();
            if (order.getConfirmCode().equals(confirm)) {
                req.setAttribute("message", "Покупка успешно совершена!");
            } else {
                req.setAttribute("message", "Неверный код. Введите заново!");
            }
        }
        req.getRequestDispatcher("/payment_confirm.jsp").forward(req, resp);
    }
}
