package com.ucm.producer.configuration;

import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {
    public static final String FANOUT_EXCHANGE = "example.fanout";
    public static final String DIRECT_EXCHANGE = "example.direct";
    public static final String TOPIC_EXCHANGE = "example.topic";

    @Bean
    public Declarables createRabbitmqSchema() {
        return new Declarables(
                new DirectExchange(DIRECT_EXCHANGE, true, false, null),
                new FanoutExchange(FANOUT_EXCHANGE, true, false, null),
                new TopicExchange(TOPIC_EXCHANGE, true, false, null));
    }
}
