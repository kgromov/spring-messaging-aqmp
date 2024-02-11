package com.aqmp.example;

import com.aqmp.example.config.BrokerSettings;
import com.aqmp.example.config.MessageBrokerConfig;
import com.aqmp.example.service.Receiver;
import com.aqmp.example.service.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageBrokerConfigTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(RabbitAutoConfiguration.class, MessageBrokerConfig.class));

//    @Disabled
    @Test
    void loadContext() {
        contextRunner
                .run((context) -> {
                    assertThat(context).hasSingleBean(BrokerSettings.class);
                    assertThat(context).hasSingleBean(RabbitTemplate.class);
                    assertThat(context).hasSingleBean(Receiver.class);
                    assertThat(context).hasSingleBean(Sender.class);
                });
    }
}
