package com.example.practo.Service;



import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class BloodReportServiceImpl
        implements BloodReportService {

    @Value("${groq.api.key}")
    private String apiKey;

    @Override
    public String analyzeReport(
            MultipartFile file) {

        try {

            // READ PDF

            PDDocument document =
                    PDDocument.load(
                            file.getInputStream());

            PDFTextStripper stripper =
                    new PDFTextStripper();

            String extractedText =
                    stripper.getText(document);

            document.close();

            // GROQ AI API

            RestTemplate restTemplate =
                    new RestTemplate();

            String url =
                    "https://api.groq.com/openai/v1/chat/completions";

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON);

            headers.setBearerAuth(apiKey);

            Map<String, Object> body =
                    new HashMap<>();

            body.put(
                    "model",
                    "llama-3.3-70b-versatile"
            );

            List<Map<String, String>> messages =
                    new ArrayList<>();

            Map<String, String> system =
                    new HashMap<>();

            system.put("role", "system");

            system.put(
                    "content",

                    "You are a medical report analyzer. "
                            + "Analyze blood report and explain "
                            + "hemoglobin, sugar, cholesterol, "
                            + "and risk levels in simple words."
            );

            Map<String, String> user =
                    new HashMap<>();

            user.put("role", "user");

            user.put(
                    "content",
                    extractedText
            );

            messages.add(system);

            messages.add(user);

            body.put("messages", messages);

            HttpEntity<Map<String, Object>> request =
                    new HttpEntity<>(body, headers);

            ResponseEntity<Map> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            request,
                            Map.class);

            List choices =
                    (List) response.getBody()
                            .get("choices");

            Map choice =
                    (Map) choices.get(0);

            Map message =
                    (Map) choice.get("message");

            return message.get("content")
                    .toString();

        }

        catch (Exception e) {

            e.printStackTrace();

            return e.getMessage();
        }
    }
}