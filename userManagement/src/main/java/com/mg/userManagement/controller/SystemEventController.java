package com.mg.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.SystemEventDTO;
import com.mg.userManagement.dtoservice.SystemEventServiceDTO;
@RestController
public class SystemEventController {

	@Autowired
	SystemEventServiceDTO systemEventServiceDTO; 
	
	@PostMapping(value = "/addEvent")
	public SystemEventDTO save(@RequestBody SystemEventDTO systemEventDTO)
	{
		System.out.println("in controller ..."+systemEventDTO);
		return systemEventServiceDTO.saveEvent(systemEventDTO); 
	}
}
