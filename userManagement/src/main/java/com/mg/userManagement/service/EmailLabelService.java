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
			logger.info("Attempting to save new email label: "+emailLabel.getName());
			return emailLabelRepository.save(emailLabel);
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid arguements passed while trying to email label: "+emailLabel.getName());
			logger.error("", ex);
		}
		catch(Exception ex)
		{
			logger.error("Exception while trying to save email label: "+emailLabel.getName());
			logger.error("", ex);
		}
		return null;
	}
	
	public EmailLabel update(Integer emailLabelId, EmailLabel emailLabel) throws Exception
	{
		Optional<EmailLabel> existingtemplateOptional = emailLabelRepository.findById(emailLabelId) ;
		
		if (existingtemplateOptional.isPresent())
		{
			EmailLabel updatedLabel = existingtemplateOptional.get();
			updatedLabel.setId(emailLabelId);
			updatedLabel.setName(emailLabel.getName());
			try {
				logger.info("Attempting to update email label: "+emailLabel.getName());
				return emailLabelRepository.save(updatedLabel);
			}
			catch(IllegalArgumentException ex) {
				logger.error("Invalid arguements passed while trying to update email label: "+emailLabel.getName());
				logger.error("", ex);
			}
			catch(Exception ex)
			{
				logger.error("Exception while trying to update email label: "+emailLabel.getName());
				logger.error("", ex);
			}
			return null;
		}
		else
		{
			throw new Exception("No labels exists for id ["+emailLabelId+"]");
		}
	}
	
	public List<EmailLabel> getAll()
	{
		logger.info("Listing all email labels");
		return emailLabelRepository.findAll();
	}

	public List<EmailLabel> getEmailLabelsByUserId(Integer userId) throws Exception{
		User user = userService.getUserById(userId);
		Optional<List<EmailLabel>> emailLabelOptional = emailLabelRepository.findByUser(user);
		if(emailLabelOptional.isPresent())
		{
			return emailLabelOptional.get();
		}
		else
		{
			throw new Exception("No label exists for userId ["+userId+"]");
		}
		
	}

	public EmailLabel getLabelById(Integer emailLabelId){
		try {
			Optional<EmailLabel> emailLabelOptional = emailLabelRepository.findById(emailLabelId);
			if (emailLabelOptional.isPresent())
			{
				return emailLabelOptional.get();
			}
			else
			{
				logger.error("Email template label not found");
			}
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid arguements passed while trying to delete email label");
			logger.error("", ex);
		}
		catch(Exception ex)
		{
			logger.error("Exception while trying to delete email label");
			logger.error("", ex);
		}
		return null;
	}

	
	public List<EmailLabel> deleteLabelbyId(Integer emailLabelId){
		try {
			Integer userId = emailLabelRepository.findById(emailLabelId).get().getUser().getId();
			emailLabelRepository.deleteById(emailLabelId);
			return this.getEmailLabelsByUserId(userId);
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid arguements passed while trying to delete email label");
			logger.error("", ex);
		}
		catch(Exception ex)
		{
			logger.error("Exception while trying to delete email label");
			logger.error("", ex);
		}
		return null;
	}

}