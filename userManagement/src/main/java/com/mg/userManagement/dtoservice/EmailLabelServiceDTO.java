package com.mg.userManagement.dtoservice;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mg.userManagement.dto.EmailLabelDTO;
import com.mg.userManagement.entity.EmailLabel;
import com.mg.userManagement.service.EmailLabelService;

@Service
public class EmailLabelServiceDTO {

	@Autowired
	private EmailLabelService emailLabelService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private EmailLabel emailLabel;
	
	public EmailLabelDTO createEmailLabel(Integer userId, EmailLabelDTO emailLabelDTO) throws Exception
	{
		emailLabel = modelMapper.map(emailLabelDTO, EmailLabel.class);
		return modelMapper.map(emailLabelService.create(userId, emailLabel), EmailLabelDTO.class);
	}
	
	public EmailLabelDTO updateEmailLabel(Integer emailLabelId, EmailLabelDTO emailLabelDTO) throws Exception
	{
		emailLabel = modelMapper.map(emailLabelDTO, EmailLabel.class);
		return modelMapper.map(emailLabelService.update(emailLabelId, emailLabel), EmailLabelDTO.class);
	}
	
	public List<EmailLabelDTO> getAll()
	{
		@SuppressWarnings("serial")
		Type listEmailLabels = new TypeToken<List<EmailLabelDTO>>() {}.getType(); 
		return modelMapper.map(emailLabelService.getAll(), listEmailLabels);
	}

	public List<EmailLabelDTO> getEmailLabelByUserId(Integer userId) throws Exception{
		@SuppressWarnings("serial")
		Type listEmailLabels = new TypeToken<List<EmailLabelDTO>>() {}.getType(); 
		return modelMapper.map(emailLabelService.getEmailLablesByUserId(userId), listEmailLabels);
	}

	public EmailLabelDTO getEmailLabelById(Integer emailLabelId) throws Exception {
		return modelMapper.map(emailLabelService.getLabelById(emailLabelId), EmailLabelDTO.class);
	}

	public EmailLabelDTO associateTemplateToRule(Integer labelId, Integer emailLabelId) throws Exception 
	{
		return modelMapper.map(emailLabelService.associateTemplate(labelId, emailLabelId), EmailLabelDTO.class);
	}
	
	public List<EmailLabelDTO> deleteEmailLabel(Integer emailLabelId)
	{
		@SuppressWarnings("serial")
		Type listEmailLabels = new TypeToken<List<EmailLabelDTO>>() {}.getType();
		return modelMapper.map(emailLabelService.deleteLabelbyId(emailLabelId), listEmailLabels);
	}
	
}
