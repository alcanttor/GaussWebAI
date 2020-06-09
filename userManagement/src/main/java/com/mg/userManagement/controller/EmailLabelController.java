package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.EmailLabelDTO;
import com.mg.userManagement.dtoservice.EmailLabelServiceDTO;


@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class EmailLabelController {

	@Autowired
	private EmailLabelServiceDTO emailLabelServiceDTO;
	
	@PostMapping(value="/addEmailLabel/{userId}")
	public EmailLabelDTO add(@RequestBody EmailLabelDTO emailLabelDTO, @PathVariable Integer userId)
	{
		try {
			return emailLabelServiceDTO.createEmailLabel(userId, emailLabelDTO);
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/getEmailLabelsbyuserid/{userId}")
	public List<EmailLabelDTO> getSitebyUser(@PathVariable Integer userId) throws Exception
	{
		return emailLabelServiceDTO.getEmailLabelByUserId(userId);
	}
	
	@PostMapping(value="/updateEmailLabel/{LabelId}")
	public EmailLabelDTO updateTemplate(@RequestBody EmailLabelDTO emailLabelDTO, @PathVariable Integer LabelId)
	{
		try {
			return emailLabelServiceDTO.updateEmailLabel(LabelId, emailLabelDTO) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/getEmailLabelById/{emailLabelId}")
	public EmailLabelDTO getById(@PathVariable Integer emailLabelId)
	{
		try {
			return emailLabelServiceDTO.getEmailLabelById(emailLabelId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/associate/{labelId}/{emailTemplateId}")
	public EmailLabelDTO associate(@PathVariable Integer labelId, @PathVariable Integer emailTemplateId)
	{
		try {
			return emailLabelServiceDTO.associateTemplateToRule(labelId,emailTemplateId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
