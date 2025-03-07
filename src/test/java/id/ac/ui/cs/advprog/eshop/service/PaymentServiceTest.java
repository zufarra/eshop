package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.*;

class PaymentServiceTest {
    private PaymentServiceImpl paymentService;
    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;

    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        orderRepository = mock(OrderRepository.class);
        paymentService = new PaymentServiceImpl(paymentRepository, orderRepository);
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");
    }

    @Test
    void testAddPaymentWithVoucher() {
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(order, PaymentMethod.VOUCHER.getValue(), paymentData);

        assertThat(payment).isNotNull();
        assertThat(payment.getMethod()).isEqualTo(PaymentMethod.VOUCHER.getValue());
        assertThat(payment.getOrder()).isEqualTo(order);
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testAddPaymentWithBankTransfer() {
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER.getValue(), paymentData);

        assertThat(payment).isNotNull();
        assertThat(payment.getMethod()).isEqualTo(PaymentMethod.BANK_TRANSFER.getValue());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testAddPaymentWithInvalidMethod() {
        assertThatThrownBy(() -> paymentService.addPayment(order, "INVALID_METHOD", paymentData))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid payment method");
    }

    @Test
    void testSetPaymentStatusToSuccess() {
        Payment payment = new Payment(PaymentMethod.VOUCHER.getValue(), paymentData, order);

        paymentService.setStatus(payment, "SUCCESS");

        assertThat(payment.getStatus()).isEqualTo("SUCCESS");
        assertThat(payment.getOrder().getStatus()).isEqualTo("SUCCESS");
    }

    @Test
    void testSetPaymentStatusToRejected() {
        Payment payment = new Payment(PaymentMethod.BANK_TRANSFER.getValue(), paymentData, order);

        paymentService.setStatus(payment, "REJECTED");

        assertThat(payment.getStatus()).isEqualTo("REJECTED");
        assertThat(payment.getOrder().getStatus()).isEqualTo("FAILED");
    }

    @Test
    void testSetInvalidPaymentStatus() {
        Payment payment = new Payment(PaymentMethod.VOUCHER.getValue(), paymentData, order);

        assertThatThrownBy(() -> paymentService.setStatus(payment, "UNKNOWN_STATUS"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid payment status");
    }

    @Test
    void testGetPaymentById() {
        Payment payment = new Payment(PaymentMethod.VOUCHER.getValue(), paymentData, order);
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentByInvalidId() {
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = List.of(
                new Payment(PaymentMethod.VOUCHER.getValue(), paymentData, order),
                new Payment(PaymentMethod.BANK_TRANSFER.getValue(), paymentData, order)
        );
        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyElementsOf(payments);
    }
}
