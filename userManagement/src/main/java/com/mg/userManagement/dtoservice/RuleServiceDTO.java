package com.mg.userManagement.dtoservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.dto.RuleDTO;
import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.service.RuleService;

@Service
public class RuleServiceDTO {

	@Autowired
	private RuleService ruleService;

	@Autowired
	private ModelMapper modelMapper;

	public RuleDTO updateRule(RuleDTO ruleDTO,Integer ruleId) throws Exception {
		Rule rule = modelMapper.map(ruleDTO, Rule.class);
		return modelMapper.map(ruleService.updateRule(rule,ruleId), RuleDTO.class);
	}
}
