package com.mg.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.RuleDTO;
import com.mg.userManagement.dtoservice.RuleServiceDTO;
@RestController
public class RuleController {

	@Autowired
	RuleServiceDTO ruleServiceDTO; 
	
	@PostMapping(value = "/updateRule")
	public RuleDTO saveAction(@RequestBody RuleDTO ruleDto , Integer ruleId)
	{
		try{
		return ruleServiceDTO.updateRule(ruleDto,ruleId); 
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
