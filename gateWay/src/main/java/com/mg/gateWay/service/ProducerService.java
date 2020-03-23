package com.mg.gateWay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mg.gateWay.model.Event;

@Service
public class ProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, Event> kafkaEventDataTemplate;
	
	
	String kafkaTopic = "test";
	
	public void send(String message) {
	    
	    kafkaTemplate.send(kafkaTopic, message);
	}
	
	public void send(Event event) {
	    
	    kafkaEventDataTemplate.send(kafkaTopic, event);
	}
}
