package com.mg.userManagement.dtoservice;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mg.userManagement.dto.EmailLableDTO;
import com.mg.userManagement.entity.EmailLable;
import com.mg.userManagement.service.EmailLableService;

@Service
public class EmailLableServiceDTO {

	@Autowired
	private EmailLableService emailLableService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private EmailLable emailLable;
	
	public EmailLableDTO createEmailLable(Integer userId, EmailLableDTO emailLableDTO) throws Exception
	{
		emailLable = modelMapper.map(emailLableDTO, EmailLable.class);
		return modelMapper.map(emailLableService.create(userId, emailLable), EmailLableDTO.class);
	}
	
	public EmailLableDTO updateEmailLable(Integer emailLableId, EmailLableDTO emailLableDTO) throws Exception
	{
		emailLable = modelMapper.map(emailLableDTO, EmailLable.class);
		return modelMapper.map(emailLableService.update(emailLableId, emailLable), EmailLableDTO.class);
	}
	
	public List<EmailLableDTO> getAll()
	{
		@SuppressWarnings("serial")
		Type listEmailLables = new TypeToken<List<EmailLableDTO>>() {}.getType(); 
		return modelMapper.map(emailLableService.getAll(), listEmailLables);
	}

	public List<EmailLableDTO> getEmailLableByUserId(Integer userId) throws Exception{
		@SuppressWarnings("serial")
		Type listEmailLables = new TypeToken<List<EmailLableDTO>>() {}.getType(); 
		return modelMapper.map(emailLableService.getByUserId(userId), listEmailLables);
	}

	public EmailLableDTO getEmailLableById(Integer emailLableId) throws Exception {
		return modelMapper.map(emailLableService.getById(emailLableId), EmailLableDTO.class);
	}

	public EmailLableDTO associateTemplateToRule(Integer lableId, Integer emailLableId) throws Exception 
	{
		return modelMapper.map(emailLableService.associateTemplate(lableId, emailLableId), EmailLableDTO.class);
	}
}
