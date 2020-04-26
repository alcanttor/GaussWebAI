package com.mg.gaussWorker.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mg.gaussWorker.email.model.EmailConstants;
import com.mg.gaussWorker.email.model.EmailData;
import com.mg.gaussWorker.model.Action;
import com.mg.gaussWorker.model.BaseEvent;
import com.mg.gaussWorker.model.RequestData;
import com.mg.gaussWorker.sms.model.SmsConstants;
import com.mg.gaussWorker.sms.model.SmsData;

/**Central service class to parse incoming action IDs
 * Needs to be further decoupled to separate out the underlying business logic*/
@Service
public class EventFactory {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	public BaseEvent generateEvent(Action action, RequestData requestData) {

		switch (action) {
		case EMAIL:
			logger.info("email event generated");
			EmailData event = new EmailData(this);
	        event.setSubject(requestData.getMetaData().get(EmailConstants.subject));
	        event.setText(requestData.getMetaData().get(EmailConstants.text));
	        event.setTo(requestData.getMetaData().get(EmailConstants.to));	
	        return event;
			
		case SMS:
			logger.info("sms event genrated");
			SmsData smsEvent = new SmsData(this);
			smsEvent.setFrom(requestData.getMetaData().get(SmsConstants.from));
			smsEvent.setText(requestData.getMetaData().get(SmsConstants.text));
			smsEvent.setTo(requestData.getMetaData().get(SmsConstants.to));
			return smsEvent;
	
		default:
			
			break;
		}
        	return null;
	}

}
