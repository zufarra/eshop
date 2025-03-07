package Validator;

import java.util.Map;

public class VoucherPayment implements PaymentValidator {
    @Override
    public boolean isValid(Map<String, String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");
        return validateVoucherCode(voucherCode);
    }
    private boolean validateVoucherCode(String voucherCode) {
        return voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP") && countDigits(voucherCode) == 8;
    }
    private int countDigits(String voucherCode) {
        int count = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }
}
