package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.entity.EmailTemplate;
import com.mg.userManagement.service.EmailTemplateService;

@RestController
public class EmailTemplateController {

	@Autowired
	private EmailTemplateService emailTemplateService;
	
	@PostMapping(value="/addEmailTemplate/{userId}")
	public EmailTemplate add(@RequestBody EmailTemplate emailTemplate, @PathVariable Integer userId)
	{
		try {
			return emailTemplateService.create(userId, emailTemplate);
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/getEmailTemplatesbyuserid/{userId}")
	public List<EmailTemplate> getSitebyUser(@PathVariable Integer userId) throws Exception
	{
		return emailTemplateService.getByUserId(userId);
	}
	
	@PostMapping(value="/updateEmailTemplate/{userId}")
	public EmailTemplate updateTemplate(@RequestBody EmailTemplate emailTemplate, @PathVariable Integer templateId)
	{
		try {
			return emailTemplateService.update(templateId, emailTemplate) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/getById/{emailTemplateId}")
	public EmailTemplate getById(@PathVariable Integer emailTemplateId)
	{
		try {
			return emailTemplateService.getById(emailTemplateId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/associate/{templateId}/{ruleId}")
	public EmailTemplate associate(@PathVariable Integer templateId, @PathVariable Integer ruleId)
	{
		try {
			return emailTemplateService.associateTemplateToRule(templateId,ruleId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
