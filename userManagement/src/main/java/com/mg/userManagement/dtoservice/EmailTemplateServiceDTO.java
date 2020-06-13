package com.mg.userManagement.dtoservice;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mg.userManagement.dto.EmailTemplateDTO;
import com.mg.userManagement.entity.EmailTemplate;
import com.mg.userManagement.service.EmailTemplateService;

@Service
public class EmailTemplateServiceDTO {

	@Autowired
	private EmailTemplateService emailTemplateService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private EmailTemplate emailTemplate;
	
	public EmailTemplateDTO createEmailTemplate(Integer userId, EmailTemplateDTO emailTemplateDTO) throws Exception
	{
		emailTemplate = modelMapper.map(emailTemplateDTO, EmailTemplate.class);
		return modelMapper.map(emailTemplateService.create(userId, emailTemplate), EmailTemplateDTO.class);
	}
	
	public EmailTemplateDTO updateEmailTemplate(Integer emailTemplateId, EmailTemplateDTO emailTemplateDTO) throws Exception
	{
		emailTemplate = modelMapper.map(emailTemplateDTO, EmailTemplate.class);
		return modelMapper.map(emailTemplateService.update(emailTemplateId, emailTemplate), EmailTemplateDTO.class);
	}
	
	public List<EmailTemplateDTO> getAll()
	{
		@SuppressWarnings("serial")
		Type listEmailTemplates = new TypeToken<List<EmailTemplateDTO>>() {}.getType(); 
		return modelMapper.map(emailTemplateService.getAll(), listEmailTemplates);
	}

	public List<EmailTemplateDTO> getEmailTemplatesByUserId(Integer userId) throws Exception{
		@SuppressWarnings("serial")
		Type listEmailTemplates = new TypeToken<List<EmailTemplateDTO>>() {}.getType(); 
		return modelMapper.map(emailTemplateService.getEmailTemplatesByUserId(userId), listEmailTemplates);
	}

	public EmailTemplateDTO getEmailTemplateById(Integer emailTemplateId) throws Exception {
		return modelMapper.map(emailTemplateService.getEmailTemplateById(emailTemplateId), EmailTemplateDTO.class);
	}

	public List<EmailTemplateDTO> deleteEmailTemplatebyId(Integer emailTemplateId) {
		Integer userId = ((emailTemplateService.getEmailTemplateById(emailTemplateId)).getUser()).getId();
		emailTemplateService.deleteEmailTemplateById(emailTemplateId) ;
		try {
			return this.getEmailTemplatesByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
