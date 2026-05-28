package com.example.practo.Service;

import com.example.practo.Entity.Payment;
import com.example.practo.Payload.PaymentDto;
import com.example.practo.Payload.PaymentVerifyDto;
import com.example.practo.Repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl
        implements PaymentService {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public String createOrder(PaymentDto dto)
            throws Exception {

        RazorpayClient client =
                new RazorpayClient(
                        keyId,
                        keySecret
                );

        JSONObject object =
                new JSONObject();

        object.put(
                "amount",
                dto.getAmount() * 100
        );

        object.put("currency", "INR");

        object.put(
                "receipt",
                "txn_123456"
        );

        Order order =
                client.orders.create(object);

        Payment payment =
                new Payment();

        payment.setOrderId(
                order.get("id")
                        .toString()
        );

        payment.setAmount(
                dto.getAmount()
        );

        payment.setStatus(
                "CREATED"
        );

        paymentRepository.save(payment);

        return order.toString();
    }

    @Override
    public String verifyPayment(
            PaymentVerifyDto dto)
            throws Exception {

        String generatedSignature =
                dto.getRazorpay_order_id()
                        + "|"
                        + dto.getRazorpay_payment_id();

        boolean isValid =
                Utils.verifySignature(
                        generatedSignature,
                        dto.getRazorpay_signature(),
                        keySecret
                );

        if(isValid){

            Payment payment =
                    paymentRepository
                            .findAll()
                            .stream()
                            .filter(p ->
                                    p.getOrderId()
                                            .equals(dto.getRazorpay_order_id()))
                            .findFirst()
                            .orElseThrow();

            payment.setStatus("SUCCESS");

            payment.setRazorpayPaymentId(
                    dto.getRazorpay_payment_id()
            );

            paymentRepository.save(payment);

            return "PAYMENT SUCCESS";

        }else{

            return "PAYMENT FAILED";
        }
    }
}