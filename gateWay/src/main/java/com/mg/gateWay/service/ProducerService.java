package com.mg.gateWay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mg.gateWay.model.Event;
import com.mg.gateWay.model.RequestData;

/**Singleton scoped Kafka template initiator service class*/

@Service
public class ProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, Event> kafkaEventDataTemplate;
	
	@Autowired
	private KafkaTemplate<String, RequestData> kafkaRequestDataTemplate;
	
	@Value("${kafka.kafkaTopic}")
	private String kafkaTopic;
	
	public void send(String message) {
	    
	    kafkaTemplate.send(kafkaTopic, message);
	}
	
	public void send(Event event) {
	    
	    kafkaEventDataTemplate.send(kafkaTopic, event);
	}
	
	public void send(RequestData event) {
	    
	    kafkaRequestDataTemplate.send(kafkaTopic, event);
	}

}
