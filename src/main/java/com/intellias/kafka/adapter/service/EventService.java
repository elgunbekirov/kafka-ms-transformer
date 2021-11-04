package com.intellias.kafka.adapter.service;

import com.o2.dpm.documentmanagement.DocumentCreationNotification;

public interface EventService {
    void process(DocumentCreationNotification event);
}
