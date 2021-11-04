package com.intellias.kafka.adapter.channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.intellias.kafka.adapter.service.EventService;
import com.o2.dpm.documentmanagement.DocumentCreationNotification;

@Component
public class EventReceiver {
    private static final Logger log = LoggerFactory.getLogger(EventReceiver.class);
    private final EventService eventService;

    @KafkaListener(
       topics = {"${application.kafka.source-topic-name}"}
    )
    public void receive(DocumentCreationNotification event) {
        log.info("Process event ='{}'", event);

        try {
            this.eventService.process(event);
        } catch (Exception e) {
            log.warn("Processing failed: {} With payload='{}'", e.getMessage(), event);
            throw e;
        }
    }

    public EventReceiver(final EventService eventService) {
        this.eventService = eventService;
    }
}
