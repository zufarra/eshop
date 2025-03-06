package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    @Test
    void testCreatePaymentWithSuccessStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");
        Payment payment = new Payment("456", PaymentMethod.VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidStatus(){
        Map<String, String> paymentData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("789", PaymentMethod.VOUCHER.getValue(), "INVALID_STATUS", paymentData);
        });
    }

    @Test
    void testCreatePaymentWithVoucherMethod(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");
        Payment payment = new Payment("101", PaymentMethod.VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
    }

    @Test
    void testCreatePaymentWithBankTransferMethod(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank ABC");
        paymentData.put("referenceCode", "REF1234");
        Payment payment = new Payment("102", PaymentMethod.BANK_TRANSFER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);

        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
    }

    @Test
    void testCreatePaymentWithInvalidMethod(){
        Map<String, String> paymentData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("103", "INVALID_METHOD", PaymentStatus.REJECTED.getValue(), paymentData);
        });
    }

    @Test
    void testCreatePaymentWithCorrectBankTransferValue(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank ABC");
        paymentData.put("referenceCode", "REF1234");

        Payment payment = new Payment("104", PaymentMethod.BANK_TRANSFER.getValue(), PaymentStatus.REJECTED.getValue(), paymentData);
        assertEquals("Bank ABC", payment.getPaymentData().get("bankName"));
        assertEquals("REF1234", payment.getPaymentData().get("referenceCode"));

    }

    @Test
    void testCreatePaymentWithOneEmptyBankTransferValue(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank ABC");
        paymentData.put("referenceCode", null);

        Payment payment = new Payment("123", PaymentMethod.BANK_TRANSFER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentWithCorrectVoucherValue(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("106", PaymentMethod.VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreatePaymentWithInvalidCharacterLongForVoucherValue(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234AB5678");
        Payment payment = new Payment("103", PaymentMethod.VOUCHER.getValue(), PaymentStatus.REJECTED.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
    @Test
    void testCreatePaymentWithInvalidPrefixLongForVoucherValue(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "1234ABC567");
        Payment payment = new Payment("103", PaymentMethod.VOUCHER.getValue(), PaymentStatus.REJECTED.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
    @Test
    void testCreatePaymentWithInvalidNumericLongForVoucherValue(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC567A");
        Payment payment = new Payment("103", PaymentMethod.VOUCHER.getValue(), PaymentStatus.REJECTED.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
