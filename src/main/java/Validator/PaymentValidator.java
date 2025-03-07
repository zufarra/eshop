package Validator;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.Map;

public interface PaymentValidator {
    boolean isValid(Map<String, String> paymentData);
}
