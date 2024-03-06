package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    OrderService orderService;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        if (order == null) {
            throw new NoSuchElementException();
        }
        paymentData.put("orderId", order.getId());
        UUID uuid = UUID.randomUUID();
        Payment payment = new Payment(uuid.toString(), method, paymentData);
        paymentRepository.save(payment);

        if (payment.getMethod().equals("VOUCHER")) {
            String voucherCode = payment.getPaymentData().get("voucherCode");
            if (voucherValidation(voucherCode)) {
                setStatus(payment, "SUCCESS");
            } else {
                setStatus(payment, "REJECTED");
            }
        } else if (payment.getMethod().equals("COD")) {
            if (hasNullValue(paymentData)) {
                setStatus(payment, "REJECTED");
            }
        }
        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        if (payment != null) {
            Payment newPayment = new Payment(payment.getId(), payment.getMethod(), payment.getPaymentData(), status);
            paymentRepository.save(newPayment);
            String id = newPayment.getPaymentData().get("orderId");
            if (newPayment.getStatus().equals("SUCCESS")) {
                orderService.updateStatus(id, status);
            } else if (newPayment.getStatus().equals("REJECTED")){
                orderService.updateStatus(id, "FAILED");
            }
            return newPayment;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public static boolean voucherValidation(String input) {
        if (input.length() != 16) {
            return false;
        }
        if (!input.startsWith("ESHOP")) {
            return false;
        }
        int numCount = 0;
        for (int i = 0; i < 16; i++) {
            if (Character.isDigit(input.charAt(i))) {
                numCount += 1;
            }
        }
        return numCount == 8;
    }

    public static boolean hasNullValue(Map<?, ?> map) {
        for (Object value : map.values()) {
            if (value == null) {
                return true;
            }
        }
        return false;
    }
}
