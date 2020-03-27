package com.mg.gaussWorker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mg.gaussWorker.model.EmailData;

@Service
public class EmailSenderService {

	@Autowired
    private JavaMailSender javaMailSender;
	
	void sendEmail(EmailData emailData) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailData.getTo());

        msg.setSubject(emailData.getSubject());
        msg.setText(emailData.getText());

        javaMailSender.send(msg);

    }
}
