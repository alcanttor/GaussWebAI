package com.mg.userManagement.dtoservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.dto.RuleGroupDTO;
import com.mg.userManagement.entity.RuleGroup;
import com.mg.userManagement.service.RuleGroupService;

@Service
public class RuleGroupServiceDTO {

	@Autowired
	private RuleGroupService ruleGroupService;

	@Autowired
	private ModelMapper modelMapper;

	public RuleGroupDTO updateRuleGroup(RuleGroupDTO ruleGroupDTO,Integer ruleGroupId) throws Exception {
		RuleGroup ruleGroup = modelMapper.map(ruleGroupDTO, RuleGroup.class);
		return modelMapper.map(ruleGroupService.updateRuleGroup(ruleGroup,ruleGroupId), RuleGroupDTO.class);
	}
}
