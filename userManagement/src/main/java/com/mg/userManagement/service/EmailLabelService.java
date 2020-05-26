package com.mg.userManagement.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mg.userManagement.entity.EmailLabel;
import com.mg.userManagement.entity.User;
import com.mg.userManagement.repo.EmailLabelRepository;

@Service
public class EmailLabelService {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailLabelRepository emailLabelRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public EmailLabel create(Integer userId, EmailLabel emailLabel) throws Exception
	{
		User user = userService.getUserById(userId);
		emailLabel.setUser(user);
		try {
			logger.info("Attempting to save new email template: "+emailLabel.getName());
			return emailLabelRepository.save(emailLabel);
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid arguements passed while trying to email template: "+emailLabel.getName());
			logger.error("", ex);
		}
		catch(Exception ex)
		{
			logger.error("Exception while trying to save email template: "+emailLabel.getName());
			logger.error("", ex);
		}
		return null;
	}
	
	public EmailLabel update(Integer emailLabelId, EmailLabel emailLabel) throws Exception
	{
		Optional<EmailLabel> existingtemplateOptional = emailLabelRepository.findById(emailLabelId) ;
		if (existingtemplateOptional.isPresent())
		{
			emailLabel.setId(emailLabelId);
			try {
				logger.info("Attempting to update email template: "+emailLabel.getName());
				return emailLabelRepository.save(emailLabel);
			}
			catch(IllegalArgumentException ex) {
				logger.error("Invalid arguements passed while trying to update email template: "+emailLabel.getName());
				logger.error("", ex);
			}
			catch(Exception ex)
			{
				logger.error("Exception while trying to update email template: "+emailLabel.getName());
				logger.error("", ex);
			}
			return null;
		}
		else
		{
			throw new Exception("No template exists for id ["+emailLabelId+"]");
		}
	}
	
	public List<EmailLabel> getAll()
	{
		logger.info("Listing all email templates");
		return emailLabelRepository.findAll();
	}

	public List<EmailLabel> getByUserId(Integer userId) throws Exception{
		User user = userService.getUserById(userId);
		Optional<List<EmailLabel>> emailLabelOptional = emailLabelRepository.findByUser(user);
		if(emailLabelOptional.isPresent())
		{
			return emailLabelOptional.get();
		}
		else
		{
			throw new Exception("No Template exists for userId ["+userId+"]");
		}
		
	}

	public EmailLabel getById(Integer emailLabelId) throws Exception {
		Optional<EmailLabel> emailLabelOptional = emailLabelRepository.findById(emailLabelId);
		if (emailLabelOptional.isPresent())
		{
			return emailLabelOptional.get();
		}
		else
		{
			throw new Exception("Email template not found");
		}
	}

	public EmailLabel associateTemplate(Integer labelId, Integer emailLabelId) throws Exception 
	{/*
		EmailLabel emailLabel = getById(templateId);
		Rule rule = ruleService.getById(ruleId);
		List<Rule> rules = emailLabel.getRules();
		if (rules == null)
		{
			rules = new ArrayList<>();
		}
		rules.add(rule);
		emailLabel.setRules(rules);
		return update(templateId, emailLabel);*/
		return null;
	}

}