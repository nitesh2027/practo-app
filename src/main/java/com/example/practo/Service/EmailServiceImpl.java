package com.example.practo.Service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @Override
    public void sendEmail(
            String to,
            String subject,
            String body) {

        Email from =
                new Email("niteshkr758@gmail.com");

        Email receiver =
                new Email(to);

        Content content =
                new Content(
                        "text/plain",
                        body
                );

        Mail mail =
                new Mail(
                        from,
                        subject,
                        receiver,
                        content
                );

        SendGrid sendGrid =
                new SendGrid(apiKey);

        Request request =
                new Request();

        try {

            request.setMethod(Method.POST);

            request.setEndpoint("mail/send");

            request.setBody(mail.build());

            Response response =
                    sendGrid.api(request);

            System.out.println(
                    "EMAIL STATUS: "
                            + response.getStatusCode()
            );

            System.out.println(
                    "EMAIL SENT SUCCESSFULLY"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}