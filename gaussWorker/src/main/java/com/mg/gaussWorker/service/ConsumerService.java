package com.mg.gaussWorker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mg.gaussWorker.model.Event;
import com.mg.gaussWorker.model.RequestData;
import com.mg.gaussWorker.config.EventFactory;
import com.mg.gaussWorker.email.model.EmailData;
import com.mg.gaussWorker.model.Actions;
import com.mg.gaussWorker.model.BaseEvent;

@Service
public class ConsumerService {

	private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);
	
	@Autowired
	private EventPublisherService eventPublisherService;
	
	@Autowired
	private EventFactory eventFactory;
	
	@KafkaListener(topics = "test", groupId="group_id")
    public void consume(String message) {
        logger.info("Message at consumer [{}]",message);
    }

/*
    @KafkaListener(topics = "test", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Event event) {
        logger.info("Event at Consumer [{}]",event);
        logger.info("Event id [{}] name [{}] data [{}]",event.getId(),event.getName());
        EmailData data = new EmailData(this);
        data.setSubject("test subje	ct");
        data.setText("this is the email test");
        data.setTo("luna.varun@gmail.com");
        eventPublisherService.publishEvent(data);
    }*/

    @KafkaListener(topics = "test", groupId = "group_json",
            containerFactory = "requestDataKafkaListenerFactory")
    public void consumeRequestData(RequestData requestData) {
        logger.info("Event at Consumer [{}]",requestData);
        List<Actions> actions = requestData.getActions();
        for (Actions action:actions)
        {
        	BaseEvent event = eventFactory.generateEvent(action,requestData);
        	eventPublisherService.publishEvent(event);
        }
        
    }
}
