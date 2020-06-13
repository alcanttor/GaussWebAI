package com.mg.userManagement.dtoservice;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mg.userManagement.dto.ActionDTO;
import com.mg.userManagement.dto.SystemEventDTO;
import com.mg.userManagement.dto.SystemRuleDTO;
import com.mg.userManagement.entity.Action;
import com.mg.userManagement.entity.SystemEvent;
import com.mg.userManagement.entity.SystemRule;
import com.mg.userManagement.service.SystemRuleService;

@Service
public class SystemRuleServiceDTO {
	
	@Autowired
	private SystemRuleService systemRuleService;
	
	@Autowired
	ModelMapper modelMapper;
	
	public SystemRuleDTO saveRule(SystemRuleDTO systemRuleDTO){
		SystemRule systemRule = modelMapper.map(systemRuleDTO, SystemRule.class);
		return modelMapper.map(systemRuleService.save(systemRule), SystemRuleDTO.class);
	}
	
	public List<SystemRuleDTO> getAllRules(){
		@SuppressWarnings("serial")
		Type listSystemRules = new TypeToken<List<SystemRuleDTO>>() {}.getType();
		return modelMapper.map(systemRuleService.getAll(), listSystemRules);		
	}
	
	public SystemRuleDTO getRuleById(Integer id) {
		return modelMapper.map(systemRuleService.get(id), SystemRuleDTO.class);
	}


	public SystemRuleDTO getRuleByConnectorId(Integer connectorId) {
		return modelMapper.map(systemRuleService.getByConnectorId(connectorId), SystemRuleDTO.class);
	}

	public Set<SystemEventDTO> getEventsByConnectorId(Integer connectorId) {
		List<SystemRule> rules = systemRuleService.getByConnectorId(connectorId);
		HashSet<SystemEventDTO> result = new HashSet<>();
		for (SystemRule rule : rules)
		{
			SystemEvent event = rule.getSystemEvent();
			result.add(modelMapper.map(event, SystemEventDTO.class));
		}
		return result;
	}

	public Set<ActionDTO> getEventsByConnectorIdandAction(Integer connectorId, Integer systemEventId) {

		List<SystemRule> rules = systemRuleService.getByConnectorId(connectorId);
		HashSet<ActionDTO> result = new HashSet<>();
		for (SystemRule rule : rules)
		{
			Action action = rule.getAction();
			result.add(modelMapper.map(action, ActionDTO.class));
		}
		return result;

	}

	public List<SystemRuleDTO> getAllRules(Integer connectorId) {
		List<SystemRule> rules = systemRuleService.getByConnectorId(connectorId);
		List<SystemRuleDTO> result = new ArrayList<>();
		for (SystemRule rule : rules)
		{
			result.add(modelMapper.map(rule, SystemRuleDTO.class));
		}
		return result;
	}
	
	
}
