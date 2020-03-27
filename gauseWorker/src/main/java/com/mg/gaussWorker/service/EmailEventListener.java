package com.mg.gaussWorker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.mg.gaussWorker.model.EmailData;

@Service
public class EmailEventListener implements ApplicationListener<EmailData> 
{
 
	@Autowired
	private EmailSenderService emailSenderService;

	@Override
    public void onApplicationEvent(EmailData email) {
        emailSenderService.sendEmail(email); 
    }
	
}
