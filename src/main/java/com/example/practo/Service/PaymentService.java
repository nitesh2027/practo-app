package com.example.practo.Service;

import com.example.practo.Payload.PaymentDto;
import com.example.practo.Payload.PaymentVerifyDto;

public interface PaymentService {

    String createOrder(PaymentDto dto)
            throws Exception;

    String verifyPayment(
            PaymentVerifyDto dto)
            throws Exception;
}