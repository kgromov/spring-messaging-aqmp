package com.aqmp.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties
public class MessageBrokerConfig {
    private final CachingConnectionFactory cachingConnectionFactory;

    @Bean
    @ConfigurationProperties(prefix = "rabbitmq")
    public BrokerSettings brokerSettings() {
        return  new BrokerSettings();
    }

    @Bean
    public Queue queue(BrokerSettings brokerSettings) {
        return QueueBuilder
                .nonDurable(brokerSettings.getQueueName())
                .build();
    }

    @Bean
    public DirectExchange exchange(BrokerSettings brokerSettings) {
        return new DirectExchange(brokerSettings.getExchangeName());
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange, BrokerSettings brokerSettings) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(brokerSettings.getRoutingKey());
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        return objectMapper;
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(MessageConverter jsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
}
