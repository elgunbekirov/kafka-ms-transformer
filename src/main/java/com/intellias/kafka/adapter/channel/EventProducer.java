package com.intellias.kafka.adapter.channel;

import com.o2.dpm.documentmanagement.DocumentCreationNotification;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class EventProducer {
    private static final Logger log = LoggerFactory.getLogger(EventProducer.class);
    private final KafkaTemplate<String, DocumentCreationNotification> producer;
    @Value("${application.kafka.sink-topic-name}")
    private String topicName;

    public void send(DocumentCreationNotification event) {
        String key = event.getEventId();
        ListenableFuture sendResult = this.producer.send(new ProducerRecord(this.topicName, key, event));

        try {
            sendResult.get();
        } catch (Exception e) {
            log.warn("Unsent result = '{}'", sendResult);
            throw new RuntimeException(String.format("Failed to send message with payload = %s  ", event), e);
        }

        log.debug("Sent result = '{}'", sendResult);
    }

    public EventProducer(final KafkaTemplate<String, DocumentCreationNotification> producer) {
        this.producer = producer;
    }
}
