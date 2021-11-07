package com.intellias.kafka.adapter.conversion;

import org.codehaus.commons.nullanalysis.NotNull;
import com.o2.dpm.documentmanagement.DocumentCreationNotification;

import io.confluent.salesforce.Case;

public final class EventConvertor {
	
    public static Case convert(@NotNull DocumentCreationNotification event) {
        return new Case(
		    		"Port-Out Declaration", 
		    		"0015r00000ItwGQAAZ",  
		    		event.getEvent().getDocument().getRelatedParty().get(0).getName(),
		    		"Port-Out",
		    		event.getEvent().getDocument().getCharacteristic().get(0).getName(),
		    		event.getEvent().getDocument().getCategory().get(0).getName(),
		    		event.getEvent().getDocument().getId(),
		    		"CASE",
		    		event.getEventType()
    		);  // DMP_SCHEMA  implementation 
    }
}
