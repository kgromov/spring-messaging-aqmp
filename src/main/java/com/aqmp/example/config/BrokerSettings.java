package com.aqmp.example.config;

import lombok.Data;

@Data
public class BrokerSettings {
    private String queueName;
    private String exchangeName;
    private String routingKey;
}
