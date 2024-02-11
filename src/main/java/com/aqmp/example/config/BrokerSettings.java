package com.aqmp.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "rabbitmq")
public record BrokerSettings(@DefaultValue("q.queue") String queueName,
                            @DefaultValue("x.exchange")String exchangeName,
                            @DefaultValue("key")String routingKey) { }

