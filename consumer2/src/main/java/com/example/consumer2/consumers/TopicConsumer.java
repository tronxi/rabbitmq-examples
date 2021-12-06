package com.example.consumer2.consumers;

import com.example.consumer2.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.consumer2.configuration.RabbitmqConfiguration.TOPIC_QUEUE;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = TOPIC_QUEUE)
public class TopicConsumer {
    private final ObjectMapper objectMapper;

    @RabbitHandler
    public void deletedUser(String event) {
        try {
            Message message = objectMapper.readValue(event, Message.class);
            System.out.println(message);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing object");
        }
    }
}