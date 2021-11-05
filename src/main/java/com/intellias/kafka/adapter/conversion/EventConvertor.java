package com.intellias.kafka.adapter.conversion;

import org.codehaus.commons.nullanalysis.NotNull;

import com.intellias.kafka.adapter.model.Case;
import com.o2.dpm.documentmanagement.DocumentCreationNotification;

public final class EventConvertor {
	
    public static Case convert(@NotNull DocumentCreationNotification event) {
        return new Case(
		        		"Port-Out Declaration", 
		        		"0015r00000ItwGQAAZ",  
		        		"8005r0000011KxhAAE",
		        		"Port-Out",
		        		event.getEvent().getDocument().getCharacteristic().get(2).toString(),
		        		event.getEvent().getDocument().getCharacteristic().get(3).toString(),
		        		"aabybxuwddngcqdsiid2lmakp5pgy",
		        		"CASE",
		        		event.getEventType()
		        		);  // DMP_SCHEMA  implementation 
    }
}
