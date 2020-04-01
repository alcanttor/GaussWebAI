package com.mg.gateWay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mg.gateWay.model.Event;
import com.mg.gateWay.model.RequestData;
import com.mg.gateWay.service.ProducerService;

@RestController
public class Producer {

	@Autowired
	ProducerService producerService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = "/producer")
	public void producer(@RequestParam("message") String message) {
		
		producerService.send(message);
		logger.debug("Message sent to the Kafka Topic Successfully");

	}
	
	@PostMapping(value = "/pushEvent")
	public void pushEvent(@RequestBody Event event) {		
		producerService.send(event);
		logger.debug("Message sent to the Kafka Topic Successfully");
	}
	
	@PostMapping(value = "/push")
	public void push(@RequestBody RequestData info) {
		try{
		producerService.send(info);
		}
		catch (Exception e) {
			logger.error("Request rejected - failure: "+e.getMessage());
			e.printStackTrace();
		}
		logger.debug("Request accepted - success");
	}
}
