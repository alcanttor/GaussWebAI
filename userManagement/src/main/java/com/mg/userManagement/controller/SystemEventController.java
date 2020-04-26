package com.mg.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.entity.SystemEvent;
import com.mg.userManagement.service.SystemEventService;

@RestController
public class SystemEventController {

	@Autowired
	SystemEventService systemEventService; 
	
	@PostMapping(value = "/addEvent")
	public SystemEvent save(@RequestBody SystemEvent systemEvent)
	{
		return systemEventService.save(systemEvent); 
	}
}
