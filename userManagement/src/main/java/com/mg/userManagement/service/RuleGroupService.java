package com.mg.userManagement.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.RuleGroup;
import com.mg.userManagement.repo.RuleGroupRepository;

@Service
public class RuleGroupService {

	@Autowired
	private RuleGroupRepository ruleGroupRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public RuleGroup save(RuleGroup ruleGroup)
	{
		return ruleGroupRepository.save(ruleGroup);
	}

	public RuleGroup updateRuleGroup(RuleGroup ruleGroup, Integer ruleGroupId) throws Exception {
		Optional<RuleGroup> rg = ruleGroupRepository.findById(ruleGroupId);
		if (rg.isPresent())
		{
			RuleGroup rgDB = rg.get();
			ruleGroup.setId(rgDB.getId());
			return ruleGroupRepository.save(ruleGroup);
		}
		else
		{
			throw new  Exception();
		}
		
	}
}
