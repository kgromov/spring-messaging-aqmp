package com.aqmp.example;

import com.aqmp.example.config.BrokerSettings;
import com.aqmp.example.service.Receiver;
import com.aqmp.example.service.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringMessagingAqmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMessagingAqmpApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(BrokerSettings brokerSettings,
                                        Sender sender,
                                        Receiver receiver,
                                        RabbitTemplate rabbitTemplate) {
        return args -> {
            sender.sendSynEvent();
//            SyncEvent syncEvent = (SyncEvent) rabbitTemplate.receiveAndConvert(brokerSettings.getQueueName());
//            log.info("Received: {}", rabbitTemplate.receiveAndConvert());
        };
    }
}
