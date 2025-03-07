package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import enums.PaymentMethod;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        if (!isValidMethod(method)) {
            throw new IllegalArgumentException("Invalid payment method");
        }
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException("Payment data cannot be empty");
        }
        if (orderHasPayment(order)) {
            throw new IllegalStateException("Order already has a payment");
        }
        if (order.getStatus().equals("SUCCESS") || order.getStatus().equals("FAILED")) {
            throw new IllegalStateException("Cannot create payment for a completed order");
        }

        Payment payment = new Payment(method, paymentData, order);
        return paymentRepository.save(payment);
    }

    public Payment setStatus(Payment payment, String status) {
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid payment status");
        }

        payment.setStatus(status);
        if (status.equals("SUCCESS")) {
            payment.getOrder().setStatus("SUCCESS");
        } else if (status.equals("REJECTED")) {
            payment.getOrder().setStatus("FAILED");
        }

        return paymentRepository.save(payment);
    }

    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }


    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    private boolean isValidMethod(String method) {
        return Arrays.stream(PaymentMethod.values())
                .map(PaymentMethod::getValue)
                .anyMatch(validMethod -> validMethod.equals(method));
    }

    private boolean isValidStatus(String status) {
        return status.equals("SUCCESS") || status.equals("REJECTED");
    }

    private boolean orderHasPayment(Order order) {
        return paymentRepository.findAll().stream()
                .anyMatch(payment -> payment.getOrder().equals(order));
    }
}
