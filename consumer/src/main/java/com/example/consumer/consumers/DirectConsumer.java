package com.example.consumer.consumers;

import com.example.consumer.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.consumer.configuration.RabbitmqConfiguration.DIRECT_QUEUE;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = DIRECT_QUEUE)
public class DirectConsumer {
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
