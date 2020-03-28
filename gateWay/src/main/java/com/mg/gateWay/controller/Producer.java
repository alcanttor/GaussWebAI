package com.mg.gateWay.controller;

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

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message) {
		producerService.send(message);

		return "Message sent to the Kafka Topic java_in_use_topic Successfully";
	}
	
	@PostMapping(value = "/pushEvent")
	public String pushEvent(@RequestBody Event event) {
		producerService.send(event);

		return "Message sent to the Kafka Topic java_in_use_topic Successfully";
	}
	@PostMapping(value = "/push")
	public String push(@RequestBody RequestData info) {
		try{
		producerService.send(info);
		}
		catch (Exception e) {
			return "Request not Accepted";
		}
		return "Request Accepted";
	}
}
