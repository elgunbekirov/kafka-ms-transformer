package com.intellias.kafka.adapter.channel;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import io.confluent.salesforce.Case;

@Component
public class EventProducer {
	
    private static final Logger log = LoggerFactory.getLogger(EventProducer.class);
    
    private final KafkaTemplate<String, Case> producer;
    
    @Value("${application.kafka.sink-topic-name}")
    private String topicName;

    public void send(Case event) {
        String key = event.getAccountId();
        ListenableFuture sendResult = this.producer.send(new ProducerRecord(this.topicName, key, event));

        try {
            sendResult.get();
        } 
        catch (Exception e) {
            log.warn("Unsent result = '{}'", sendResult);
            throw new RuntimeException(String.format("Failed to send message with payload = %s  ", event), e);
        }

        log.debug("Sent result = '{}'", sendResult);
    }

    public EventProducer(final KafkaTemplate<String, Case> producer) {
        this.producer = producer;
    }
}
