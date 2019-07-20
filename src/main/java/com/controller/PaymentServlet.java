package com.controller;

import com.factory.CodeServiceFactory;
import com.factory.MailServiceFactory;
import com.factory.OrderServiceFactory;
import com.model.Code;
import com.model.Order;
import com.model.User;
import com.service.CodeService;
import com.service.MailService;
import com.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    private static final MailService mailService = MailServiceFactory.getInstance();
    private static final OrderService orderService = OrderServiceFactory.getInstance();
    private static final CodeService codeService = CodeServiceFactory.getInstance();

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
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        String phoneNumber = req.getParameter("phone");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        codeService.add(new Code(user));

        Code code = null;
        Optional<Code> optionalCode = codeService.getLastCodeForUser(user);
        if (optionalCode.isPresent()) {
            code = optionalCode.get();
        }

        Order order = new Order(user, code, email, phoneNumber, address);
        orderService.add(order);
        new Thread(() -> mailService.sendConfirmCode(order)).start();

        resp.sendRedirect("/payment/confirm");
    }
}
