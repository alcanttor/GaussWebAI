package com.mg.eventConsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mg.eventConsumer.model.Event;

@Service
public class ConsumerService {

	private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);
	
	@KafkaListener(topics = "test", groupId="group_id")
    public void consume(String message) {
        logger.info("Message at consumer [{}]",message);
    }


    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Event event) {
        logger.info("Event at Consumer [{}]",event);
        logger.info("Event id [{}] name [{}] data [{}]",event.getId(),event.getName(),event.getEventData());
    }
}
