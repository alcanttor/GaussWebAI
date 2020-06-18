package com.mg.gaussWorker.sms.service;

import com.mg.gaussWorker.model.BaseEvent;
import com.mg.gaussWorker.model.RequestData;
import com.mg.gaussWorker.sms.model.SmsConstants;
import com.mg.gaussWorker.sms.model.SmsData;

public class SmsWrapper {

	public BaseEvent prepareSmsObject(RequestData requestData) {
		SmsData smsEvent = new SmsData(this);
		smsEvent.setFrom(requestData.getMetaData().get(SmsConstants.from));
		smsEvent.setText(requestData.getMetaData().get(SmsConstants.text));
		smsEvent.setTo(requestData.getMetaData().get(SmsConstants.to));
		return smsEvent;
	}
}
