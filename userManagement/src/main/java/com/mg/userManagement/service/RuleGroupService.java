package com.mg.userManagement.service;

import java.util.List;
import java.util.Optional;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.entity.RuleGroup;
import com.mg.userManagement.repo.RuleGroupRepository;

@Service
public class RuleGroupService {

	@Autowired
	private RuleGroupRepository ruleGroupRepository;
	
	@Autowired
	private RuleService ruleService;
	
	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public RuleGroup save(RuleGroup ruleGroup)
	{
		return ruleGroupRepository.save(ruleGroup);
	}

	public RuleGroup updateRuleGroup(RuleGroup ruleGroup, Integer ruleGroupId) throws Exception {
		Optional<RuleGroup> rg = ruleGroupRepository.findById(ruleGroupId);
		if (rg.isPresent())
		{
			RuleGroup rgDB = rg.get();
			saveRules(ruleGroup.getRules());
			ruleGroup.setId(rgDB.getId());
			return ruleGroupRepository.save(ruleGroup);
		}
		else
		{
			throw new  Exception();
		}
		
	}

	private void saveRules(List<Rule> rules) throws Exception {
		for(Rule rule : rules)
		ruleService.mergeRule(rule);
		
	}
}
