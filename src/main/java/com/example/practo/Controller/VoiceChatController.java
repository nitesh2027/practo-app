package com.example.practo.Controller;

import com.example.practo.Payload.ChatRequestDto;
import com.example.practo.Payload.ChatResponseDto;
import com.example.practo.Service.ChatbotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voice-ai")

@CrossOrigin("*")

public class VoiceChatController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/ask")

    public ChatResponseDto askVoiceQuestion(
            @RequestBody ChatRequestDto request) {

        String result =
                chatbotService.askQuestion(
                        request.getQuestion());

        ChatResponseDto response =
                new ChatResponseDto();

        response.setAnswer(result);

        return response;
    }
}