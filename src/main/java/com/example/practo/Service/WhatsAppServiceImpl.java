package com.example.practo.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppServiceImpl
        implements WhatsAppService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.whatsapp.number}")
    private String fromNumber;

    @PostConstruct
    public void init() {

        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendWhatsAppMessage(
            String to,
            String message) {

        Message response =
                Message.creator(

                        new com.twilio.type.PhoneNumber(
                                "whatsapp:" + to
                        ),

                        new com.twilio.type.PhoneNumber(
                                fromNumber
                        ),

                        message

                ).create();

        System.out.println(
                "WHATSAPP SID: "
                        + response.getSid());

        System.out.println(
                "WhatsApp Message Sent Successfully");
    }
}