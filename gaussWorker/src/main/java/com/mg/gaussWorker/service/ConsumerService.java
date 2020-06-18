package com.mg.gaussWorker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mg.gaussWorker.model.RequestData;
import com.mg.gaussWorker.model.RuleDTO;
import com.mg.gaussWorker.config.EventFactory;
import com.mg.gaussWorker.model.BaseEvent;

@Service
public class ConsumerService {

	private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);
	
	@Autowired
	private EventPublisherService eventPublisherService;
	
	@Autowired
	private EventFactory eventFactory;
	
    @KafkaListener(topics = "test", groupId = "group_json",
            containerFactory = "requestDataKafkaListenerFactory")
    public void consumeRequestData(RequestData requestData) {
        logger.info("Event at Consumer [{}]",requestData);
        
       List<RuleDTO> rules = requestData.getRules();
       
       for(RuleDTO rule: rules) {
    	   logger.info("event published [{}]", rule);
    	   BaseEvent event = eventFactory.generateEvent(rule, requestData);
    	   eventPublisherService.publishEvent(event);
       }        
    }
}
