package com.intellias.kafka.adapter.conversion;

import org.codehaus.commons.nullanalysis.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.o2.dpm.documentmanagement.DocumentCreationNotification;
import com.o2.dpm.documentmanagement.DocumentCreationNotificationPayload;

public final class EventConvertor {
	
    private static final Logger log = LoggerFactory.getLogger(EventConvertor.class);

    private EventConvertor() {
    }

    public static DocumentCreationNotification convert(@NotNull DocumentCreationNotification event) {
        return new DocumentCreationNotification(event.getEventId(), event.getEventTime(), event.getEventType(), event.getCorrelationId(), event.getDomain(), event.getTitle(), event.getDescription(), event.getPriority(), event.getTimeOcurred(), new DocumentCreationNotificationPayload() );  // DMP_SCHEMA  implementation 
    }
}
