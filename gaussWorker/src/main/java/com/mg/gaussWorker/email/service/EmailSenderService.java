package com.mg.gaussWorker.email.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mg.gaussWorker.email.model.EmailData;

@Service
public class EmailSenderService {

	Logger logger = LoggerFactory.getLogger(EmailSenderService.class);
	@Autowired
	private JavaMailSender javaMailSender;

	void sendEmail(EmailData emailData) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(emailData.getTo());
			msg.setSubject(emailData.getSubject());
			msg.setText(emailData.getText());
			javaMailSender.send(msg);
			logger.info("Email send to [{}]",emailData.getTo());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Unable to send message to [{}]",emailData.getTo(),e);
		}
	}
}
