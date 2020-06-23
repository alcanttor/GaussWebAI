package com.mg.userManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.EmailLabel;
import com.mg.userManagement.entity.EmailTemplate;
import com.mg.userManagement.entity.User;
import com.mg.userManagement.repo.EmailTemplateRepository;

@Service
public class EmailTemplateService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailLabelService emailLabelService;

	@Autowired
	private EmailTemplateRepository emailTemplateRepository;
	
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

	public List<EmailTemplate> getEmailTemplatesByUserId(Integer userId)
	{
		try 
		{
			logger.info("Attempting to get userId for the template");
			User user = userService.getUserById(userId);
			Optional<List<EmailTemplate>> emailTemplateOptional = emailTemplateRepository.findByUser(user);
			if(emailTemplateOptional.isPresent())
			{
				return emailTemplateOptional.get();
			}
			else
			{
				logger.error("No Template exists for userId ["+userId+"]");
			}
			
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid arguements passed while trying to get userid");
			logger.error("", ex);
		}
		catch(Exception ex)
		{
			logger.error("Exception while trying to get userId");
			logger.error("", ex);
		}
		return null;
	}

	public EmailTemplate getEmailTemplateById(Integer emailTemplateId){
		try {
			Optional<EmailTemplate> emailTemplateOptional = emailTemplateRepository.findById(emailTemplateId);
			if (emailTemplateOptional.isPresent())
			{
				return emailTemplateOptional.get();
			}
			else
			{
				logger.error("Email template not found");
			}
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid arguements passed while trying to get email tenplate");
			logger.error("", ex);
		}
		catch(Exception ex)
		{
			logger.error("Exception while trying to get emaiul template");
			logger.error("", ex);
		}
		return null;
	}

	public boolean deleteEmailTemplateById(Integer emailTemplateId) {

		try {
			logger.info("Attempting to delete Email Template");
			emailTemplateRepository.deleteById(emailTemplateId);
			logger.info("Deletion success");
			return true;
		} catch (IllegalArgumentException ex) {
			logger.error("Invalid Arguement. Deletion process for email template failed: ", ex);
			return false;
		}
		catch(Exception ex) {
			logger.error("Invalid Arguement. Deletion process for email template failed: ", ex);
			return false;
		}
		
	}

	public List<EmailTemplate> getEmailTemplatesbyLabelId(Integer labelId, Integer userId){
		try {
			logger.info("Retrieving all emailTemplates");
			List<EmailTemplate> emailTemplates = this.getEmailTemplatesByUserId(userId);
			
			List<EmailTemplate> filteredTemplates = new ArrayList<EmailTemplate>();
			logger.info("Filtering emailTemplates based on labels");
			for(EmailTemplate template: emailTemplates) {
				List<EmailLabel> emailLabels = template.getLabels();
				
				for(EmailLabel label:emailLabels) {
					if((label.getId()).equals(labelId)) {
						filteredTemplates.add(template);
						break;
					}
				}
			}
			
			return filteredTemplates;
			
		}catch(IllegalArgumentException ex) {
			logger.error("Invalid arguements passed while trying to get email tenplate");
			logger.error("", ex);
		}
		catch(Exception ex)
		{
			logger.error("Exception while trying to get email template");
			logger.error("", ex);
		}
		return null;
	}
	
	public boolean associateLable(List<EmailTemplate> emailTemplates, Integer labelId)
	{
		EmailTemplate emailTemplateUpdate = new EmailTemplate();
		
		List<EmailLabel> listLabel = new ArrayList<EmailLabel>();		
		listLabel.add(emailLabelService.getLabelById(labelId));
		
		for(EmailTemplate emailTemplate: emailTemplates) {
			try {
				Optional<EmailTemplate> emailTemplateOptional = emailTemplateRepository.findById(emailTemplate.getId());
				if (emailTemplateOptional.isPresent())
				{
					emailTemplateUpdate = emailTemplateOptional.get();
					emailTemplateUpdate.setLabels(listLabel);
					emailTemplateRepository.save(emailTemplateUpdate);
				}
			}
			catch(IllegalArgumentException ex) {
				logger.error("", ex);
			}
			catch(Exception ex)
			{
				logger.error("", ex);
			}
		}
		return true;
	}

}