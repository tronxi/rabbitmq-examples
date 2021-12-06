package com.example.stompproducer.producer;

import com.example.stompproducer.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class StompProducer {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void send() {
        Message message = new Message();
        message.setType("stomp");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        message.setMessage(dtf.format(now));
        try {
            String stringMessage = objectMapper.writeValueAsString(message);
            simpMessagingTemplate.convertAndSend("/topic/message", stringMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

