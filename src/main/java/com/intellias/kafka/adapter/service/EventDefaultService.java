package com.intellias.kafka.adapter.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.intellias.kafka.adapter.channel.EventProducer;
import com.intellias.kafka.adapter.conversion.EventConvertor;
import com.o2.dpm.documentmanagement.DocumentCreationNotification;

@Component
public class EventDefaultService implements EventService {
    private static final Logger log = LoggerFactory.getLogger(EventDefaultService.class);
    private final EventProducer eventProducer;

    public void process(final DocumentCreationNotification event) {
        Optional.ofNullable(event)
                .map(EventConvertor::convert)
                .ifPresent(eventProducer::send);
    }

    public EventProducer getEventProducer() {
        return this.eventProducer;
    }

    public EventDefaultService(final EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }
}
