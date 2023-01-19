package com.aqmp.example.service;

import com.aqmp.example.events.SyncEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Sender {
    private final RabbitTemplate rabbitTemplate;

    public void sendSynEvent() {
        log.info("About to send sync event");
//        rabbitTemplate.convertAndSend("weatherQueue", SyncEvent.createDefault());
        rabbitTemplate.convertAndSend("weatherQueue", "Hello, rabbit!");
        log.info("Sent sync event");
    }
}
