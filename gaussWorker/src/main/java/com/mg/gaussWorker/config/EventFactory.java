package com.mg.gaussWorker.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mg.gaussWorker.email.service.EmailWrapper;
import com.mg.gaussWorker.model.ActionList;
import com.mg.gaussWorker.model.BaseEvent;
import com.mg.gaussWorker.model.RequestData;
import com.mg.gaussWorker.model.RuleDTO;
import com.mg.gaussWorker.sms.service.SmsWrapper;

/**Central service class to parse incoming action IDs*/
@Service
public class EventFactory {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	public BaseEvent generateEvent(RuleDTO rule, RequestData requestData) {

		String actionName = rule.getSystemRule().getAction().getName();

		switch (ActionList.get(actionName)) {
		case EMAIL:
			logger.info("email event generated");
			EmailWrapper emailWrapper = new EmailWrapper();
			return emailWrapper.prepareEmailObject(rule, requestData);
			/*EmailData event = new EmailData(this);
	        event.setSubject(requestData.getMetaData().get(EmailConstants.subject));
	        event.setText(requestData.getMetaData().get(EmailConstants.text));
	        event.setTo(requestData.getMetaData().get(EmailConstants.to));	
	        return event;*/
			
		case SMS:
			logger.info("sms event genrated");
			SmsWrapper smsWrapper = new SmsWrapper();
			return smsWrapper.prepareSmsObject(requestData);
	
		default:
			
			break;
		}
        	return null;
	}

}
