package com.aqmp.example.service;

import com.aqmp.example.events.SyncEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
@RequiredArgsConstructor
public class Receiver {
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "q.sync-weather-queue")
    public void onSyncEvent(SyncEvent event) {
        log.info("Event {} received", event);
    }
}
