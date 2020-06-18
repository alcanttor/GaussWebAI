package com.mg.gaussWorker.sms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.mg.gaussWorker.sms.model.SmsData;

@Service
public class SmsEventListener implements ApplicationListener<SmsData> {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(SmsData smsData) {
	
		logger.info(" I am going to send sms now");
		logger.info(" I am going to send sms now sms data ["+smsData+"]" );
	}

}
