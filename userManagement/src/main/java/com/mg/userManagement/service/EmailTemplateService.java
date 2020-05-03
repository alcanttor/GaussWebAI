package com.mg.userManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.emitter.Emitable;

import com.mg.userManagement.entity.EmailTemplate;
import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.entity.Site;
import com.mg.userManagement.entity.User;
import com.mg.userManagement.repo.EmailTemplateRepository;
import com.mg.userManagement.repo.SiteRepository;
import com.mg.userManagement.repo.UserRepository;

@Service
public class EmailTemplateService {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailTemplateRepository emailTemplateRepository;
	
	@Autowired
	private RuleService ruleService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public EmailTemplate create(Integer UserId, EmailTemplate emailTemplate) throws Exception
	{
		User user = userService.getUserById(UserId);
		emailTemplate.setUser(user);
		return emailTemplateRepository.save(emailTemplate);
	}
	
	public EmailTemplate update(Integer emailTemplateId, EmailTemplate emailTemplate) throws Exception
	{
		Optional<EmailTemplate> existingtemplateOptional = emailTemplateRepository.findById(emailTemplateId) ;
		if (existingtemplateOptional.isPresent())
		{
			emailTemplate.setId(emailTemplateId);
			return emailTemplateRepository.save(emailTemplate);
		}
		else
		{
			throw new Exception("Template not exist for id ["+emailTemplateId+"]");
		}
	}
	
	public List<EmailTemplate> getAll()
	{
		return emailTemplateRepository.findAll();
	}

	public List<EmailTemplate> getByUserId(Integer userId) throws Exception{
		User user = userService.getUserById(userId);
		Optional<List<EmailTemplate>> emailTemplateOptional = emailTemplateRepository.findByUser(user);
		if(emailTemplateOptional.isPresent())
		{
			return emailTemplateOptional.get();
		}
		else
		{
			throw new Exception("No Template for userId ["+userId+"]");
		}
		
	}

	public EmailTemplate getById(Integer emailTemplateId) throws Exception {
		Optional<EmailTemplate> emailTemplateOptional = emailTemplateRepository.findById(emailTemplateId);
		if (emailTemplateOptional.isPresent())
		{
			return emailTemplateOptional.get();
		}
		else
		{
			throw new Exception("Email template not found");
		}
	}

	public EmailTemplate associateTemplateToRule(Integer templateId, Integer ruleId) throws Exception 
	{
		EmailTemplate emailTemplate = getById(templateId);
		Rule rule = ruleService.getById(ruleId);
		List<Rule> rules = emailTemplate.getRules();
		if (rules == null)
		{
			rules = new ArrayList<>();
		}
		rules.add(rule);
		emailTemplate.setRules(rules);
		return update(templateId, emailTemplate);
	}

}