package com.example.practo.Payload;

import lombok.Data;

@Data
public class PaymentVerifyDto {

    private String razorpay_order_id;

    private String razorpay_payment_id;

    private String razorpay_signature;
}