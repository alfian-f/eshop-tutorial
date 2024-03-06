package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @InjectMocks
    OrderServiceImpl orderService;


    @Mock
    PaymentRepository paymentRepository;
    @Mock
    OrderRepository orderRepository;

    List<Payment> payments;
    List<Order> orders;

    Map<String, String> voucherData;
    Map<String, String> codData;

    @BeforeAll
    static void mocking() {
        UUID mockUUID = UUID.fromString("2176d4b5-2b9f-4c21-9a58-23692ebcefbf");
        mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(mockUUID);
    }

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment1 = new Payment("1","voucherCode", paymentData);
        payments.add(payment1);
        Payment payment2 = new Payment("2","voucherCode", paymentData);
        payments.add(payment2);
        Payment payment3 = new Payment("3","voucherCode", paymentData);
        payments.add(payment3);

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);

        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
    }

    @Test
    void testAddPayment() {
        Map<String, String> voucherPayment = new HashMap<>();
        voucherPayment.put("voucherCode", "ESHOP1234ABC5678");

        Order order = orders.get(1);
        Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(), order.getAuthor(), OrderStatus.SUCCESS.getValue());

        Payment result = paymentService.addPayment(newOrder, "voucherCode", voucherPayment);

        assertEquals(newOrder.getId(), result.getPaymentData().get("orderId"));
    }

    @Test
    void testAddPaymentNoOrder() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "voucherCode", voucherData);
        assertThrows(NoSuchElementException.class, () -> paymentService.addPayment(null,payment.getMethod(), payment.getPaymentData()));
    }

    @Test
    void testSetStatusNoPayment() {
        assertThrows(NoSuchElementException.class, () -> paymentService.setStatus(null,PaymentStatus.SUCCESS.getValue()));
    }

    @Test
    void testGetPaymentIfPaymentExists() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());

        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfPaymentDoesNotExist() {
        doReturn(null).when(paymentRepository).findById("invalidPaymentId");

        assertNull(paymentService.getPayment("invalidPaymentId"));
    }

    @Test
    void testGetAllPayments() {
        Payment payment = payments.get(1);
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(3, results.size());
    }

    @Test
    void testVoucherValid() {
        String vouchercode = "ESHOP1234ABC5678";
        assertTrue(paymentService.voucherValidation(vouchercode));
    }

    @Test
    void testVoucherInvalidLength() {
        String vouchercode = "ESHOP1234ABC56789";
        assertFalse(paymentService.voucherValidation(vouchercode));
    }

    @Test
    void testVoucherInvalidStart() {
        String vouchercode = "PSHOP1234ABC5678";
        assertFalse(paymentService.voucherValidation(vouchercode));
    }

    @Test
    void testVoucherInvalidNumCount() {
        String vouchercode = "PSHOP12349BC5678";
        assertFalse(paymentService.voucherValidation(vouchercode));
    }

    @Test
    void  testHashMapHasNull() {
        Map<String, String> CODData = new HashMap<>();
        CODData.put("address", null);
        CODData.put("deliveryFee", "10000");

        assertTrue(paymentService.hasNullValue(CODData));
    }

    @Test
    void  testHashMapNoNull() {
        Map<String, String> CODData = new HashMap<>();
        CODData.put("address", "Gedung Baru Fasilkom");
        CODData.put("deliveryFee", "10000");

        assertFalse(paymentService.hasNullValue(CODData));
    }
}