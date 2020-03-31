package com.mg.gaussWorker.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mg.gaussWorker.email.model.EmailConstants;
import com.mg.gaussWorker.email.model.EmailData;
import com.mg.gaussWorker.model.Actions;
import com.mg.gaussWorker.model.BaseEvent;
import com.mg.gaussWorker.model.RequestData;
import com.mg.gaussWorker.sms.model.SmsConstants;
import com.mg.gaussWorker.sms.model.SmsData;
@Service
public class EventFactory {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	public BaseEvent generateEvent(Actions action, RequestData requestData) {

		switch (action) {
		case EMAIL:
			logger.info("email event generate");
			EmailData event = new EmailData(this);
	        event.setSubject(requestData.getMetaData().get(EmailConstants.subject));
	        event.setText(requestData.getMetaData().get(EmailConstants.text));
	        event.setTo(requestData.getMetaData().get(EmailConstants.to));	
	        return event;
			
		case SMS:
			logger.info("sms event genrate");
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
