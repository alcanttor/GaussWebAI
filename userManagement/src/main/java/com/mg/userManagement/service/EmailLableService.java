package com.mg.userManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mg.userManagement.entity.EmailLable;
import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.entity.User;
import com.mg.userManagement.repo.EmailLableRepository;
import com.mg.userManagement.repo.EmailLableRepository;

@Service
public class EmailLableService {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailLableRepository emailLableRepository;
	
	@Autowired
	private RuleService ruleService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public EmailLable create(Integer userId, EmailLable emailLable) throws Exception
	{
		User user = userService.getUserById(userId);
		emailLable.setUser(user);
		try {
			logger.info("Attempting to save new email template: "+emailLable.getName());
			return emailLableRepository.save(emailLable);
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid arguements passed while trying to email template: "+emailLable.getName());
			logger.error("", ex);
		}
		catch(Exception ex)
		{
			logger.error("Exception while trying to save email template: "+emailLable.getName());
			logger.error("", ex);
		}
		return null;
	}
	
	public EmailLable update(Integer emailLableId, EmailLable emailLable) throws Exception
	{
		Optional<EmailLable> existingtemplateOptional = emailLableRepository.findById(emailLableId) ;
		if (existingtemplateOptional.isPresent())
		{
			emailLable.setId(emailLableId);
			try {
				logger.info("Attempting to update email template: "+emailLable.getName());
				return emailLableRepository.save(emailLable);
			}
			catch(IllegalArgumentException ex) {
				logger.error("Invalid arguements passed while trying to update email template: "+emailLable.getName());
				logger.error("", ex);
			}
			catch(Exception ex)
			{
				logger.error("Exception while trying to update email template: "+emailLable.getName());
				logger.error("", ex);
			}
			return null;
		}
		else
		{
			throw new Exception("No template exists for id ["+emailLableId+"]");
		}
	}
	
	public List<EmailLable> getAll()
	{
		logger.info("Listing all email templates");
		return emailLableRepository.findAll();
	}

	public List<EmailLable> getByUserId(Integer userId) throws Exception{
		User user = userService.getUserById(userId);
		Optional<List<EmailLable>> emailLableOptional = emailLableRepository.findByUser(user);
		if(emailLableOptional.isPresent())
		{
			return emailLableOptional.get();
		}
		else
		{
			throw new Exception("No Template exists for userId ["+userId+"]");
		}
		
	}

	public EmailLable getById(Integer emailLableId) throws Exception {
		Optional<EmailLable> emailLableOptional = emailLableRepository.findById(emailLableId);
		if (emailLableOptional.isPresent())
		{
			return emailLableOptional.get();
		}
		else
		{
			throw new Exception("Email template not found");
		}
	}

	public EmailLable associateTemplate(Integer lableId, Integer emailLableId) throws Exception 
	{/*
		EmailLable emailLable = getById(templateId);
		Rule rule = ruleService.getById(ruleId);
		List<Rule> rules = emailLable.getRules();
		if (rules == null)
		{
			rules = new ArrayList<>();
		}
		rules.add(rule);
		emailLable.setRules(rules);
		return update(templateId, emailLable);*/
		return null;
	}

}