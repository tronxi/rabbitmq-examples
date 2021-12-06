package com.example.consumer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {
    public static final String QUEUE = "example.fanout.consumer1";
    public static final String EXCHANGE = "example.fanout";

    public static final String DIRECT_EXCHANGE = "example.direct";
    public static final String DIRECT_QUEUE = "example.direct.consumer1";

    public static final String TOPIC_EXCHANGE = "example.topic";
    public static final String TOPIC_QUEUE = "example.topic.consumer1";

    @Bean
    public Declarables createRabbitmqSchema() {
        return new Declarables(
                new Queue(QUEUE),
                new Binding(QUEUE, Binding.DestinationType.QUEUE, EXCHANGE, "", null),

                new Queue(DIRECT_QUEUE),
                new Binding(DIRECT_QUEUE, Binding.DestinationType.QUEUE, DIRECT_EXCHANGE, "consumer1", null),

                new Queue(TOPIC_QUEUE),
                new Binding(TOPIC_QUEUE, Binding.DestinationType.QUEUE, TOPIC_EXCHANGE, "message.consumer1.#", null)
                );
    }
}