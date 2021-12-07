package com.ucm.producer.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {
    public static final String FANOUT_EXCHANGE = "example.fanout";
    public static final String DIRECT_EXCHANGE = "example.direct";
    public static final String TOPIC_EXCHANGE = "example.topic";
    public static final String DEAD_LETTER_EXCHANGE = "example.dead.letter";
    public static final String DEAD_LETTER_QUEUE = "example.dead.letter.queue";

    @Bean
    public Declarables createRabbitmqSchema() {
        return new Declarables(
                new Queue(DEAD_LETTER_QUEUE),
                new FanoutExchange(DEAD_LETTER_EXCHANGE, true, false, null),
                new Binding(DEAD_LETTER_QUEUE, Binding.DestinationType.QUEUE, DEAD_LETTER_EXCHANGE, "", null),

                new DirectExchange(DIRECT_EXCHANGE, true, false, null),
                new DirectExchange(DIRECT_EXCHANGE, true, false, null),
                new FanoutExchange(FANOUT_EXCHANGE, true, false, null),
                new TopicExchange(TOPIC_EXCHANGE, true, false, null));
    }
}
