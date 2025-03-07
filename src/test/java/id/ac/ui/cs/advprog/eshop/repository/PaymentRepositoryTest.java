package id.ac.ui.cs.advprog.eshop.repository;

import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    OrderRepository orderRepository;
    List<Payment> payments;
    List<Order> orders;
    List<Product> products;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        OrderRepository orderRepository = new OrderRepository();

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products, 1708560000L, "Safira Sudrajat");
        orders.add(order2);
        orderRepository.save(order1);
        orderRepository.save(order2);


        paymentRepository = new PaymentRepository();

        payments = new ArrayList<>();
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP12345678ABC");
        Payment payment1 = new Payment(PaymentMethod.VOUCHER.getValue(), paymentData1, order1);
        payments.add(payment1);
        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "Bank BCA");
        paymentData2.put("referenceCode", "REF1234");
        Payment payment2 = new Payment(PaymentMethod.BANK_TRANSFER.getValue(), paymentData2, order2);
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
        Payment newPayment = new Payment(payment.getMethod(), payment.getPaymentData(), payment.getOrder());
        newPayment.setId(payment.getId());
        newPayment.setStatus(PaymentStatus.REJECTED.getValue());

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
    void testFindByOrderIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> findResults = paymentRepository.findByOrderId(orders.get(1).getId());
        assertFalse(findResults.isEmpty());
        assertEquals(1, findResults.size());
        assertEquals(orders.get(1).getId(), findResults.get(0).getOrder().getId());
    }

    @Test
    void testFindByOrderIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> findResults = paymentRepository.findByOrderId("nonexistent-order-id");
        assertTrue(findResults.isEmpty());
    }



}
