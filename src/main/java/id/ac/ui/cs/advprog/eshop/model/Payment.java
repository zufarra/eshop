package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import Validator.BankTransferPayment;
import Validator.VoucherPayment;

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
    private VoucherPayment voucherPaymentValidator = new VoucherPayment();
    private BankTransferPayment bankTransferPaymentValidator= new BankTransferPayment();


    public Payment(String method, Map<String, String> paymentData, Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException("Payment Data cannot be null");
        }
        this.id = UUID.randomUUID().toString();
        this.paymentData = paymentData;
        this.order = order;
        if (!PaymentMethod.contains(method)) {
            throw new IllegalArgumentException("Invalid payment method");
        }
        this.method = method;
        validatePaymentData();

    }
    private void validatePaymentData() {
        if (this.method.equals(PaymentMethod.VOUCHER.getValue())) {
            validateVoucherPayment();
        } else if (this.method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            validateBankTransferPayment();
        }
    }

    private void validateVoucherPayment() {
        if (voucherPaymentValidator.isValid(this.paymentData)) {
            this.status = PaymentStatus.SUCCESS.getValue();
        } else {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }

    private void validateBankTransferPayment() {
        if (bankTransferPaymentValidator.isValid(this.paymentData)) {
            this.status = PaymentStatus.SUCCESS.getValue();
        } else {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }


}