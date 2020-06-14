package com.mg.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.ActionDTO;
import com.mg.userManagement.dtoservice.ActionServiceDTO;

@RestController
public class ActionController {

	@Autowired
	ActionServiceDTO actionServiceDTO; 
	
	@PostMapping(value = "/addaction")
	public ActionDTO saveAction(@RequestBody ActionDTO actionDTO)
	{
		return actionServiceDTO.saveAction(actionDTO); 
	}
}
