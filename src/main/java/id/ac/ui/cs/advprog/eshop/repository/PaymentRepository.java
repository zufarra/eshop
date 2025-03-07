package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> allPayment = new ArrayList<>();

    public Payment save (Payment payment) {
        int i = 0;
        for (Payment savedPayment : allPayment) {
            if (savedPayment.getId().equals(payment.getId())) {
                allPayment.remove(i);
                allPayment.add(i, payment);
                return payment;
            }
            i++;
        }
        allPayment.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        for(Payment savedPayment : allPayment) {
            if (savedPayment.getId().equals(id)) {
                return savedPayment;
            }
        }
        return null;
    }
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        payments.addAll(allPayment);
        return payments;
    }

    public List<Payment> findAllByMethod(String method) {
        List<Payment> payments = new ArrayList<>();
        for (Payment savedPayment : allPayment) {
            if (savedPayment.getMethod().equals(method)) {
                payments.add(savedPayment);
            }
        }
        return payments;
    }
}
