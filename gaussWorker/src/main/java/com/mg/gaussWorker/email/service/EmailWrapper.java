package com.mg.gaussWorker.email.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mg.gaussWorker.email.model.EmailConstants;
import com.mg.gaussWorker.email.model.EmailData;
import com.mg.gaussWorker.model.BaseEvent;
import com.mg.gaussWorker.model.RequestData;
import com.mg.gaussWorker.model.RuleDTO;
import com.mg.gaussWorker.service.ConsumerService;

public class EmailWrapper {
	
	private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);
	
	public BaseEvent prepareEmailObject(RuleDTO rule, RequestData requestData){
		EmailData event = new EmailData(this);
		
		logger.info("Set common fields");
		event.setTo(requestData.getMetaData().get(EmailConstants.to));	
		event.setSubject(requestData.getMetaData().get(EmailConstants.subject));
		
		if(rule.getEmailTemplate()!=null)
		{
			logger.info("Use custom email template");
			event.setText(rule.getEmailTemplate().getTemplate());
		}
		else {
			logger.info("Use default template");
			event.setText(requestData.getMetaData().get(EmailConstants.text));
		}
        
        return event;
	}
}
