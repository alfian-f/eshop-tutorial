package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentTest {
    private Map<String, String> voucherData;
    private Map<String, String> codData;

    @BeforeEach
    void setUp() {
        Map<String, String> voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");

        Map<String, String> codData = new HashMap<>();
        codData.put("address", "Gedung Baru Fasilkom");
        codData.put("deliveryFee", "20000");
    }

    @Test
    void testCreatePaymentVoucher() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "voucherCode", voucherData);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals(voucherData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentVoucherVALID() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "voucherCode", paymentData, "SUCCESS");
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("voucherCode", payment.getMethod());

        String voucherCode = payment.paymentData.get("voucherCode");
        assertEquals("ESHOP1234ABC5678", voucherCode);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherINVALID() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678");
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "voucherCode", paymentData, "REJECTED");
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("voucherCode", payment.getMethod());

        String voucherCode = payment.paymentData.get("voucherCode");
        assertNotEquals("ESHOP1234ABC5678", voucherCode);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherSuccessStatus() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "voucherCode", voucherData, "SUCCESS");
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejectedStatus() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "voucherCode", voucherData, "REJECTED");
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "voucherCode", voucherData, "MEOW");
        });
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "voucherCode", voucherData);
        payment.setStatus("SUCCESS");
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "voucherCode", voucherData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

}