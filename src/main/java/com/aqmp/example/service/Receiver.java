package com.aqmp.example.service;

import com.aqmp.example.events.SyncEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Receiver {
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener
//    public void listenToEvent(SyncEvent syncEvent) {
    public void listenToEvent(String message) {
        log.info("Event {} received", message);
    }
}
