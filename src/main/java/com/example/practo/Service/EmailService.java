package com.example.practo.Service;

public interface EmailService {

    void sendEmail(
            String to,
            String subject,
            String body
    );
}