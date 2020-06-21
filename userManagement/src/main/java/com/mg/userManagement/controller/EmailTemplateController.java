package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.EmailTemplateDTO;
import com.mg.userManagement.dtoservice.EmailTemplateServiceDTO;

@RestController
public class EmailTemplateController {

	@Autowired
	private EmailTemplateServiceDTO emailTemplateServiceDTO;
	
	@PostMapping(value="/addemailtemplate/{userId}")
	public EmailTemplateDTO add(@RequestBody EmailTemplateDTO emailTemplateDTO, @PathVariable Integer userId)
	{
		try {
			return emailTemplateServiceDTO.createEmailTemplate(userId, emailTemplateDTO);
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/getemailtemplatesbyuserid/{userId}")
	public List<EmailTemplateDTO> getSitebyUser(@PathVariable Integer userId) throws Exception
	{
		return emailTemplateServiceDTO.getEmailTemplatesByUserId(userId);
	}
	
	@PostMapping(value="/updateemailtemplate/{templateId}")
	public EmailTemplateDTO updateTemplate(@RequestBody EmailTemplateDTO emailTemplateDTO, @PathVariable Integer templateId)
	{
		try {
			return emailTemplateServiceDTO.updateEmailTemplate(templateId, emailTemplateDTO) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/getbyid/{emailTemplateId}")
	public EmailTemplateDTO getById(@PathVariable Integer emailTemplateId)
	{
		try {
			return emailTemplateServiceDTO.getEmailTemplateById(emailTemplateId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/deletetemplatebyid/{emailTemplateId}")
	public List<EmailTemplateDTO> deleteTemplate(@PathVariable Integer emailTemplateId)
	{
		return emailTemplateServiceDTO.deleteEmailTemplatebyId(emailTemplateId);
	}
	
	@GetMapping(value="/gettemplatesbylabelid/{labelId}/{userId}")
	public List<EmailTemplateDTO> getById(@PathVariable Integer labelId, @PathVariable Integer userId)
	{
		return emailTemplateServiceDTO.getEmailTemplatesByLabelId(labelId, userId);
	}
}
