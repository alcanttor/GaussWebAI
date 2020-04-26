package com.mg.gateWay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.gateWay.model.HoaderResponse;
import com.mg.gateWay.model.RequestData;
import com.mg.gateWay.service.ProducerService;

@RestController
public class Producer 
{

	@Autowired
	ProducerService producerService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping(value = "/push")
	public HoaderResponse push(@RequestBody RequestData info) 
	{
		try 
		{
			logger.info("request received at controller....");
			return producerService.send(info);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.error("Request rejected - failure: " + e.getMessage());
			HoaderResponse response = new HoaderResponse(false,"Request not in format");
			return response;
		}
	}
}
