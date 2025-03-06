package id.ac.ui.cs.advprog.eshop.model;

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

        // Validasi status
        if (status != null && !status.equals("SUCCESS") && !status.equals("FAILED") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Invalid payment status");
        }

        // Validasi metode pembayaran
        if (!method.equals("VOUCHER") && !method.equals("BANK_TRANSFER")) {
            throw new IllegalArgumentException("Invalid payment method");
        }

        // Validasi data spesifik sesuai metode pembayaran
        if (method.equals("VOUCHER")) {
            if (!paymentData.containsKey("voucherCode")) {
                this.status = "REJECTED";
                return;
            }

            String voucherCode = paymentData.get("voucherCode");

            // Voucher harus 16 karakter, diawali "ESHOP", dan minimal ada 8 digit angka
            if (voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
                this.status = "REJECTED";
                return;
            }

            int digitCount = 0;
            for (char c : voucherCode.toCharArray()) {
                if (Character.isDigit(c)) {
                    digitCount++;
                }
            }
            if (digitCount < 8) {
                this.status = "REJECTED";
            }
        }

        if (method.equals("BANK_TRANSFER")) {
            if (!paymentData.containsKey("bankName") || !paymentData.containsKey("referenceCode")) {
                this.status = "REJECTED";
                return;
            }

            String bankName = paymentData.get("bankName");
            String referenceCode = paymentData.get("referenceCode");

            if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
                this.status = "REJECTED";
            }
        }
    }
}
