package com.mg.userManagement.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.ActionDTO;
import com.mg.userManagement.dto.SystemEventDTO;
import com.mg.userManagement.dto.SystemRuleDTO;
import com.mg.userManagement.dtoservice.SystemRuleServiceDTO;

@RestController
public class SystemRuleController {

	@Autowired
	SystemRuleServiceDTO systemRuleServiceDTO; 
	
	@PostMapping(value = "/addSystemRule")
	public SystemRuleDTO save(@RequestBody SystemRuleDTO systemRule)
	{
		return systemRuleServiceDTO.saveRule(systemRule); 
	}
	
	@GetMapping(value = "/getSystemRule")
	public List<SystemRuleDTO> getAll()
	{
		return systemRuleServiceDTO.getAllRules(); 
	}
	
	@GetMapping(value = "/getSystemRule/{siteId}")
	public SystemRuleDTO getBySiteId(@PathVariable Integer siteId)
	{
		return systemRuleServiceDTO.getRuleBySiteId(siteId); 
	}
	
	@GetMapping(value = "/getEventsbyConnector/{connectorId}")
	public Set<SystemEventDTO> getbyConnectorId(@PathVariable Integer connectorId)
	{
		return systemRuleServiceDTO.getEventsByConnectorId(connectorId); 
	}
	
	@GetMapping(value = "/getActionsByConnectorsAndActions/{ConnectorId}/{SystemEventId}")
	public Set<ActionDTO> getActionsByEventId(@PathVariable Integer ConnectorId,@PathVariable Integer SystemEventId)
	{
		return systemRuleServiceDTO.getEventsByConnectorIdandAction(ConnectorId,SystemEventId); 
	}
}
