package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.entity.SystemRule;
import com.mg.userManagement.service.SystemRuleService;

@RestController
public class SystemRuleController {

	@Autowired
	SystemRuleService systemRuleService; 
	
	@PostMapping(value = "/addSystemRule")
	public SystemRule save(@RequestBody SystemRule systemRule)
	{
		return systemRuleService.save(systemRule); 
	}
	
	@GetMapping(value = "/getSystemRule")
	public List<SystemRule> getAll()
	{
		return systemRuleService.getAll(); 
	}
	
	@GetMapping(value = "/getSystemRule/{siteId}")
	public SystemRule get(@PathVariable Integer siteId)
	{
		return systemRuleService.get(siteId); 
	}
	
}
