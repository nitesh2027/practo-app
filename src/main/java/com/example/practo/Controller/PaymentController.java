package com.example.practo.Controller;

import com.example.practo.Payload.PaymentDto;
import com.example.practo.Payload.PaymentVerifyDto;
import com.example.practo.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public String createOrder(
            @RequestBody PaymentDto dto)
            throws Exception {

        return paymentService
                .createOrder(dto);
    }

    @PostMapping("/verify")
    public String verifyPayment(
            @RequestBody PaymentVerifyDto dto)
            throws Exception {

        return paymentService
                .verifyPayment(dto);
    }
}