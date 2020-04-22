package com.mg.userManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.repo.RuleRepository;

@Service
public class RuleService {

	@Autowired
	private RuleRepository ruleRepository;
	
	public Rule save(Rule rule)
	{
		return ruleRepository.save(rule);
	}
	
	public List<Rule> saveList(List<Rule> rules)
	{
		List<Rule> savedRules = new ArrayList<>();
		for(Rule rule: rules)
		{
			savedRules.add(this.save(rule));
		}
		return savedRules;
	}
	
}
