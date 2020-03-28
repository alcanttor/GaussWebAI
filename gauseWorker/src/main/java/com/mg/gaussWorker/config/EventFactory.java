package com.mg.gaussWorker.config;

import org.springframework.stereotype.Service;

import com.mg.gaussWorker.email.model.EmailConstants;
import com.mg.gaussWorker.email.model.EmailData;
import com.mg.gaussWorker.model.Actions;
import com.mg.gaussWorker.model.BaseEvent;
import com.mg.gaussWorker.model.RequestData;
@Service
public class EventFactory {

	public BaseEvent generateEvent(Actions action, RequestData requestData) {

		switch (action) {
		case EMAIL:
			EmailData event = new EmailData(this);
	        event.setSubject(requestData.getMetaData().get(EmailConstants.subject));
	        event.setText(requestData.getMetaData().get(EmailConstants.text));
	        event.setTo(requestData.getMetaData().get(EmailConstants.to));	
	        return event;
			
		case SMS:
			
			break;
	
		default:
			
			break;
		}
        	return null;
	}

}
