package com.mg.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.RuleGroupDTO;
import com.mg.userManagement.dtoservice.RuleGroupServiceDTO;
@RestController
public class RuleGroupController {

	@Autowired
	RuleGroupServiceDTO ruleGroupServiceDTO; 
	
	@PostMapping(value = "/updaterulegroup/{ruleGroupId}")
	public RuleGroupDTO saveAction(@RequestBody RuleGroupDTO ruleGroupDto , @PathVariable Integer ruleGroupId)
	{
		try{
		return ruleGroupServiceDTO.updateRuleGroup(ruleGroupDto,ruleGroupId); 
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
