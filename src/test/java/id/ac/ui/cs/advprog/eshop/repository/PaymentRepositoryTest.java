package id.ac.ui.cs.advprog.eshop.repository;

import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        payments = new ArrayList<>();
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP12345678ABC");
        Payment payment1 = new Payment("123", PaymentMethod.VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData1);
        payments.add(payment1);
        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "Bank BCA");
        paymentData2.put("referenceCode", "REF1234");
        Payment payment2 = new Payment("456", PaymentMethod.BANK_TRANSFER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData2);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate(){
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testSaveUpdate(){
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), PaymentStatus.REJECTED.getValue(), payment.getPaymentData());
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
    }

    @Test
    void testFindByIdFound(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdNotFound(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testFindAll(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> allPayments = paymentRepository.findAll();
        assertEquals(payments.size(), allPayments.size());

        for (Payment payment : payments) {
            assertTrue(allPayments.contains(payment));
        }
    }

    @Test
    void testFindAllByMethodIfMethodCorrect(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> paymentList = paymentRepository.findAllByMethod(payments.get(1).getMethod());
        assertEquals(1, paymentList.size());
    }

    @Test
    void testFindAllByMethodIfAllLowerCase(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> paymentList = paymentRepository.findAllByMethod(payments.get(1).getMethod().toLowerCase());
        assertTrue(paymentList.isEmpty());
    }


}
