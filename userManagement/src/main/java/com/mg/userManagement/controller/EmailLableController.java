package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.EmailLableDTO;
import com.mg.userManagement.dtoservice.EmailLableServiceDTO;
@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class EmailLableController {

	@Autowired
	private EmailLableServiceDTO emailLableServiceDTO;
	
	@PostMapping(value="/addEmailLable/{userId}")
	public EmailLableDTO add(@RequestBody EmailLableDTO emailLableDTO, @PathVariable Integer userId)
	{
		try {
			return emailLableServiceDTO.createEmailLable(userId, emailLableDTO);
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/getEmailLablesbyuserid/{userId}")
	public List<EmailLableDTO> getSitebyUser(@PathVariable Integer userId) throws Exception
	{
		return emailLableServiceDTO.getEmailLableByUserId(userId);
	}
	
	@PostMapping(value="/updateEmailLable/{LableId}")
	public EmailLableDTO updateTemplate(@RequestBody EmailLableDTO emailLableDTO, @PathVariable Integer LableId)
	{
		try {
			return emailLableServiceDTO.updateEmailLable(LableId, emailLableDTO) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/getEmailLableById/{emailLableId}")
	public EmailLableDTO getById(@PathVariable Integer emailLableId)
	{
		try {
			return emailLableServiceDTO.getEmailLableById(emailLableId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/associate/{lableId}/{emailTemplateId}")
	public EmailLableDTO associate(@PathVariable Integer lableId, @PathVariable Integer emailTemplateId)
	{
		try {
			return emailLableServiceDTO.associateTemplateToRule(lableId,emailTemplateId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
