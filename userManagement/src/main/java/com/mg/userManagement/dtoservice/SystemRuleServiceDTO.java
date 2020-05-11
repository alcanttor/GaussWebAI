package com.mg.userManagement.dtoservice;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mg.userManagement.dto.SystemRuleDTO;
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
	
}
