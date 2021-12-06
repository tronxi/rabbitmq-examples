package com.ucm.producer.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucm.producer.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ucm.producer.configuration.RabbitmqConfiguration.FANOUT_EXCHANGE;

@RestController
@RequestMapping("producer/fanout")
@RequiredArgsConstructor
public class FanoutController {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    @GetMapping("/{msg}")
    public Message produce(@PathVariable String msg) {
        Message message = new Message();
        message.setMessage(msg);
        message.setType("fanout");
        try {
            String stringMessage = objectMapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend(FANOUT_EXCHANGE, "", stringMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }
}
