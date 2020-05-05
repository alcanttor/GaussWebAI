package com.mg.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.entity.Action;
import com.mg.userManagement.service.ActionService;

@RestController
public class ActionController {

	@Autowired
	ActionService actionService; 
	
	@PostMapping(value = "/addAction")
	public Action save(@RequestBody Action action)
	{
		return actionService.save(action); 
	}
}
