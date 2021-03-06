package com.mg.userManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.repo.RuleRepository;

@Service
public class RuleService {

	@Autowired
	private RuleRepository ruleRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Rule save(Rule rule)
	{
		return ruleRepository.save(rule);
	}
	 
	public List<Rule> saveList(List<Rule> rules)
	{
		List<Rule> savedRules = new ArrayList<>();
		for(Rule rule: rules)
		{
			try {
				savedRules.add(this.save(rule));
			}
			catch(IllegalArgumentException ex) {
				logger.error("Invalid Arguement. Process for rule [{}] creation failed", rule.getId());
				logger.error("", ex);
			}
			catch(Exception ex) {
				logger.error("Process for rule [{}] creation failed", rule.getId());
				logger.error("", ex);
			}
		}
		return savedRules;
	}

	public Rule getById(Integer ruleId) throws Exception {
		Optional<Rule> ruleOptional = ruleRepository.findById(ruleId);
		if (ruleOptional.isPresent())
			return ruleOptional.get();
		else {
				logger.error("Rule not found");
				throw new Exception("Rule not found");
		}
	}

	public Rule updateRule(Rule rule, Integer ruleId) throws Exception 
	{
		Optional<Rule> ruleOptional = ruleRepository.findById(ruleId);
		if (ruleOptional.isPresent())
		{
			Rule ruleinDb = ruleOptional.get();
			rule.setId(ruleinDb.getId());
			return ruleRepository.save(rule);
		}
		else 
		{
				logger.error("Rule not found");
				throw new Exception("Rule not found");
		}
	}
	
	public Rule mergeRule(Rule rule) throws Exception 
	{
		
		Optional<Rule> ruleOptional = ruleRepository.findBysystemRuleId(rule.getSystemRule().getId());
		if (ruleOptional.isPresent())
		{
			Rule ruleinDb = ruleOptional.get();
			rule.setId(ruleinDb.getId());
			return ruleRepository.save(rule);
		}
		else 
		{
			return ruleRepository.save(rule);
		}
	}
	
	
}
