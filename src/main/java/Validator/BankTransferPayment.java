package Validator;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.Map;

public class BankTransferPayment implements PaymentValidator {
    @Override
    public boolean isValid(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        return !(bankName == null) && !bankName.isEmpty() && !(referenceCode == null) && !referenceCode.isEmpty();
    }
}
