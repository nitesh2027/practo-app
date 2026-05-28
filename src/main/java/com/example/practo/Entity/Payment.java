package com.example.practo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private String orderId;

    private String razorpayPaymentId;

    private Integer amount;

    private String status;
}