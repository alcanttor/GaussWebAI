package com.mg.userManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mg.userManagement.entity.EmailTemplate;
import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.entity.User;
import com.mg.userManagement.repo.EmailTemplateRepository;

@Service
public class EmailTemplateService {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailTemplateRepository emailTemplateRepository;
	
	@Autowired
	private RuleService ruleService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public EmailTemplate create(Integer userId, EmailTemplate emailTemplate) throws Exception
	{
		User user = userService.getUserById(userId);
		emailTemplate.setUser(user);
		try {
			logger.info("Attempting to save new email template: "+emailTemplate.getName());
			return emailTemplateRepository.save(emailTemplate);
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid arguements passed while trying to email template: "+emailTemplate.getName());
			logger.error("", ex);
		}
		catch(Exception ex)
		{
			logger.error("Exception while trying to save email template: "+emailTemplate.getName());
			logger.error("", ex);
		}
		return null;
	}
	
	public EmailTemplate update(Integer emailTemplateId, EmailTemplate emailTemplate) throws Exception
	{
		Optional<EmailTemplate> existingtemplateOptional = emailTemplateRepository.findById(emailTemplateId) ;
		if (existingtemplateOptional.isPresent())
		{
			emailTemplate.setId(emailTemplateId);
			try {
				logger.info("Attempting to update email template: "+emailTemplate.getName());
				return emailTemplateRepository.save(emailTemplate);
			}
			catch(IllegalArgumentException ex) {
				logger.error("Invalid arguements passed while trying to update email template: "+emailTemplate.getName());
				logger.error("", ex);
			}
			catch(Exception ex)
			{
				logger.error("Exception while trying to update email template: "+emailTemplate.getName());
				logger.error("", ex);
			}
			return null;
		}
		else
		{
			throw new Exception("No template exists for id ["+emailTemplateId+"]");
		}
	}
	
	public List<EmailTemplate> getAll()
	{
		logger.info("Listing all email templates");
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
			throw new Exception("No Template exists for userId ["+userId+"]");
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

	

}