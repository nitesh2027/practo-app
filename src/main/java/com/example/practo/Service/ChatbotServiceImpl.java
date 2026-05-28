package com.example.practo.Service;

import com.example.practo.Entity.ChatHistory;
import com.example.practo.Repository.ChatHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ChatbotServiceImpl
        implements ChatbotService {

    @Autowired
    private ChatHistoryRepository
            chatHistoryRepository;

    @Value("${groq.api.key}")
    private String apiKey;

    @Override
    public String askQuestion(String question) {

        try {

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

                    "You are a medical assistant. "
                            + "Give short healthcare guidance. "
                            + "Always recommend consulting doctor "
                            + "for serious issues."
            );

            Map<String, String> user =
                    new HashMap<>();

            user.put("role", "user");

            user.put("content", question);

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

            String aiResponse =
                    message.get("content")
                            .toString();

            ChatHistory history =
                    new ChatHistory();

            history.setQuestion(question);

            history.setAnswer(aiResponse);

            chatHistoryRepository
                    .save(history);

            return aiResponse;

        }

        catch (Exception e) {

            e.printStackTrace();

            return e.getMessage();
        }
    }
}