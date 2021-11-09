package com.intellias.kafka.adapter;

import java.util.Iterator;

import org.apache.avro.Schema;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.trevni.avro.RandomData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.concurrent.ListenableFuture;

import com.google.gson.Gson;
import com.o2.dpm.documentmanagement.DocumentCreationNotification;


@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
	
    private final KafkaTemplate<String, DocumentCreationNotification> producer;
    
    @Value("${application.kafka.source-topic-name}")
    private String topicName;

    public void run(String... args) throws Exception {
        String key = "1";
                        
        String content = new String(FileCopyUtils
        		.copyToByteArray(ResourceUtils.getFile("classpath:input.json")), "UTF-8");
        
//        Schema schema = new Schema.Parser().parse(content);
         
        
        Gson g = new Gson();
        DocumentCreationNotification event = g.fromJson(content, DocumentCreationNotification.class);

//        Iterator<Object> it = new RandomData(schema, 1).iterator();
//        System.out.println(it.next());
        
        ListenableFuture sendResult = this.producer.send(new ProducerRecord(this.topicName, key, event));

        try 
        {
            sendResult.get();
        } 
        catch (Exception e) {
            throw new RuntimeException(String.format("Failed to send message with payload = %s  ", event), e);
        }
    }

    public CommandLineAppStartupRunner(final KafkaTemplate<String, DocumentCreationNotification> producer) {
        this.producer = producer;
    }
}
