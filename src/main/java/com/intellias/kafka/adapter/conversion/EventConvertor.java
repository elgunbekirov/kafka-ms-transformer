package com.intellias.kafka.adapter.conversion;

import java.util.List;

import org.codehaus.commons.nullanalysis.NotNull;

import com.o2.dpm.documentmanagement.CategoryRef;
import com.o2.dpm.documentmanagement.DocumentCharacteristic;
import com.o2.dpm.documentmanagement.DocumentCreationNotification;
import com.o2.dpm.documentmanagement.RelatedPartyRef;

import io.confluent.salesforce.Case;

public final class EventConvertor {
	
    public static Case convert(@NotNull DocumentCreationNotification event) {
    	List<CategoryRef> categoryList = event.getEvent().getDocument().getCategory();
    	    	
    	String type = categoryList
    			.stream()
    			.filter(cat -> cat.getName().equals("customer"))
    			.findFirst().get().getName();
    	    	
    	
    	List<RelatedPartyRef> relatedPartyList = event.getEvent().getDocument().getRelatedParty();

    	String accountId = relatedPartyList
    			.stream()
    			.filter(par -> par.getName().equals("customer"))
    			.findFirst().get().getId();

    	
    	List<DocumentCharacteristic> characteristicList = event.getEvent().getDocument().getCharacteristic();
    	
    	
    	String contractC = characteristicList
    			.stream()
    			.filter(chr -> chr.getName().equals("contractid"))
    			.findFirst().get().getValue();
    	
    	String msisdnC = characteristicList
    			.stream()
    			.filter(chr -> chr.getName().equals("msisdn"))
    			.findFirst().get().getValue();
    	    	
    	
        return new Case(type,             // Type = record.event.document.category [?(@.name = ‘name’)][0].value,
		    		accountId,            // AccountId = record.event.document.relatedParty [?(@.name = ‘customer’)][0].value
		    		contractC,            // Contract_c = record.event.document.characteristic[?(@.name = ‘contractid’)][0].value
		    		"Case Sub Type C",    // Not present in DPM payload 
		    		msisdnC,              // MSISDN__c = record.event.document.characteristic[?(@.name = ‘mssidn’)][0].value
		    		"Case Detail C ",     // Not present in DPM payload 
		    		event.getEvent().getDocument().getId(),                               // record.event.document.id
		    		"CASE",
		    		event.getEventType()
    		);  
    }
}
