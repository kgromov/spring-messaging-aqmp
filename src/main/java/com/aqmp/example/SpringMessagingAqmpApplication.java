package com.aqmp.example;

import com.aqmp.example.events.SyncEvent;
import com.aqmp.example.service.Receiver;
import com.aqmp.example.service.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class SpringMessagingAqmpApplication {
    private static final boolean NON_DURABLE = false;
    private static final String MY_QUEUE_NAME = "myQueue";

    public static void main(String[] args) {
        SpringApplication.run(SpringMessagingAqmpApplication.class, args);
    }

    @Bean
    public Queue weatherQueue() {
        return new Queue("weather-archive-queue", false);
    }

    @Bean
    ApplicationRunner applicationRunner(Sender sender, Receiver receiver, RabbitTemplate rabbitTemplate) {
        return args -> {
            sender.sendSynEvent();
//            SyncEvent syncEvent = (SyncEvent) rabbitTemplate.receiveAndConvert();
            log.info("Received: {}", rabbitTemplate.receiveAndConvert());
        };
    }

  /*  @Bean
    public ApplicationRunner runner(RabbitTemplate template) {
        return args -> {
            template.convertAndSend("myQueue", "Hello, world!");
        };
    }

    @Bean
    public Queue myQueue() {
        return new Queue(MY_QUEUE_NAME, NON_DURABLE);
    }

    @RabbitListener(queues = MY_QUEUE_NAME)
    public void listen(String in) {
        log.info("Message read from myQueue : " + in);
    }*/

}
