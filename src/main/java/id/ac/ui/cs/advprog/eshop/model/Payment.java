package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    public Payment(String method, Map<String, String> paymentData, Order order) {
        if (!isValidMethod(method)) {
            throw new IllegalArgumentException("Invalid payment method");
        }
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if ("SUCCESS".equals(order.getStatus()) || "FAILED".equals(order.getStatus())) {
            throw new IllegalStateException("Cannot create payment for a completed order");
        }
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException("Payment data cannot be empty");
        }

        this.id = UUID.randomUUID().toString();
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;
    }

    private boolean isValidMethod(String method) {
        return Arrays.stream(PaymentMethod.values())
                .map(PaymentMethod::getValue)
                .anyMatch(validMethod -> validMethod.equals(method));
    }
}
