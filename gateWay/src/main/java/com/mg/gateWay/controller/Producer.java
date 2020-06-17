package com.mg.gateWay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.gateWay.model.HoarderResponse;
import com.mg.gateWay.model.RequestData;
import com.mg.gateWay.service.ProducerService;

@RestController
public class Producer 
{

	@Autowired
	ProducerService producerService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping(value = "/push")
	public HoarderResponse push(@RequestBody RequestData requestData) 
	{
		try 
		{
			logger.debug("request received at controller....");
			return producerService.send(requestData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.error("Request rejected - failure: " + e.getMessage());
			HoarderResponse response = new HoarderResponse(false,"Request not in format");
			return response;
		}
	}
}