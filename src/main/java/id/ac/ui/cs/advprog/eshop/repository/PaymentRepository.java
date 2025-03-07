package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PaymentRepository {
    private List<Payment> allPayment = new ArrayList<>();

    public Payment save (Payment payment) {
        for (int i = 0; i < allPayment.size(); i++) {
            if (allPayment.get(i).getId().equals(payment.getId())) {
                allPayment.set(i, payment);
                return payment;
            }
        }
        allPayment.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        return allPayment.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public List<Payment> findAll() {
        return new ArrayList<>(allPayment);
    }

    public List<Payment> findAllByMethod(String method) {
        return allPayment.stream()
                .filter(payment -> payment.getMethod().equals(method))
                .collect(Collectors.toList());
    }
}
