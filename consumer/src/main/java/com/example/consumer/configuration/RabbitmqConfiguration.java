package com.example.consumer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfiguration {
    public static final String QUEUE = "example.fanout.consumer1";
    public static final String EXCHANGE = "example.fanout";
    public static final String DEAD_LETTER_EXCHANGE = "example.dead.letter";

    public static final String DIRECT_EXCHANGE = "example.direct";
    public static final String DIRECT_QUEUE = "example.direct.consumer1";

    public static final String TOPIC_EXCHANGE = "example.topic";
    public static final String TOPIC_QUEUE = "example.topic.consumer1";

    @Bean
    public Declarables createRabbitmqSchema() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-message-ttl", 10000);
        return new Declarables(
                new Queue(QUEUE),
                new Binding(QUEUE, Binding.DestinationType.QUEUE, EXCHANGE, "", null),

                new Queue(DIRECT_QUEUE, true, false, false, args),
                new Binding(DIRECT_QUEUE, Binding.DestinationType.QUEUE, DIRECT_EXCHANGE, "consumer1", null),

                new Queue(TOPIC_QUEUE),
                new Binding(TOPIC_QUEUE, Binding.DestinationType.QUEUE, TOPIC_EXCHANGE, "message.consumer.#", null)
                );
    }
}