package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.status = status;
        this.paymentData = paymentData;

        validateStatus();
        validateMethod();
        validatePaymentData();
    }

    private void validateStatus() {
        if (status != null && !status.equals(PaymentStatus.SUCCESS.getValue()) && !status.equals(PaymentStatus.FAILED.getValue()) && !status.equals(PaymentStatus.REJECTED.getValue())) {
            throw new IllegalArgumentException("Invalid payment status");
        }
    }

    private void validateMethod() {
        if (!PaymentMethod.VOUCHER.getValue().equals(method) && !PaymentMethod.BANK_TRANSFER.getValue().equals(method)) {
            throw new IllegalArgumentException("Invalid payment method");
        }
    }

    private void validatePaymentData() {
        if (paymentData == null || paymentData.isEmpty()) {
            return;
        }

        switch (method) {
            case "VOUCHER":
                validateVoucher();
                break;
            case "BANK_TRANSFER":
                validateBankTransfer();
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method");
        }
    }

    private void validateVoucher() {
        String voucherCode = paymentData.get("voucherCode");

        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP") || countDigits(voucherCode) < 8) {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }

    private void validateBankTransfer() {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }

    private int countDigits(String str) {
        return (int) str.chars().filter(Character::isDigit).count();
    }
}
