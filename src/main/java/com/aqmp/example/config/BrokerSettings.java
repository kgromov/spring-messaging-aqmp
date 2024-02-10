package com.aqmp.example.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "rabbitmq")
public class BrokerSettings {
    private final String queueName;
    private final String exchangeName;
    private final String routingKey;

     BrokerSettings(@DefaultValue("q.queue") String queueName,
                    @DefaultValue("x.exchange")String exchangeName,
                    @DefaultValue("key")String routingKey) {
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }
}
