package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PaymentTest {
    private List<Product> products;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products, 1708560000L, "Safira Sudrajat");
        orders.add(order2);
    }

    @Test
    void testCreatePaymentWithValidParams() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");

        Order order = orders.getFirst();
        Payment payment = new Payment(PaymentMethod.VOUCHER.getValue(), paymentData, order);

        assertThat(payment).isNotNull();
        assertThat(payment.getId()).isNotEmpty();
        assertThat(payment.getMethod()).isEqualTo(PaymentMethod.VOUCHER.getValue());
        assertThat(payment.getPaymentData()).containsEntry("voucherCode", "ESHOP12345678ABC");
        assertThat(payment.getOrder()).isEqualTo(order);
    }

    @Test
    void testCreatePaymentWithInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "123456789");

        Order order = orders.getFirst();

        assertThatThrownBy(() -> new Payment("INVALID_METHOD", paymentData, order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid payment method");
    }

    @Test
    void testCreatePaymentWithNullOrder() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "123456789");

        assertThatThrownBy(() -> new Payment(PaymentMethod.VOUCHER.name(), paymentData, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Order cannot be null");
    }

    @Test
    void testCreatePaymentWithEmptyPaymentData() {
        Map<String, String> paymentData = new HashMap<>();
        Order order = orders.getFirst();

        assertThatThrownBy(() -> new Payment(PaymentMethod.VOUCHER.getValue(), paymentData, order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Payment Data cannot be null");
    }

    @Test
    void testUniquePaymentIdGeneration() {
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP12345678ABC");
        Payment payment1 = new Payment(PaymentMethod.VOUCHER.getValue(), paymentData1, orders.get(0));

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOP98765432XYZ");
        Payment payment2 = new Payment(PaymentMethod.BANK_TRANSFER.getValue(), paymentData2, orders.get(1));

        assertThat(payment1.getId()).isNotEqualTo(payment2.getId());
    }
}
