package com.mg.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.RuleGroupDTO;
import com.mg.userManagement.dtoservice.RuleGroupServiceDTO;
@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class RuleGroupController {

	@Autowired
	RuleGroupServiceDTO ruleGroupServiceDTO; 
	
	@PostMapping(value = "/updateRuleGroup")
	public RuleGroupDTO saveAction(@RequestBody RuleGroupDTO ruleGroupDto , Integer ruleGroupId)
	{
		try{
		return ruleGroupServiceDTO.updateRuleGroup(ruleGroupDto,ruleGroupId); 
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
