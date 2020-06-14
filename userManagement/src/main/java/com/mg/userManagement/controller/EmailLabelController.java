package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.EmailLabelDTO;
import com.mg.userManagement.dtoservice.EmailLabelServiceDTO;


@RestController
public class EmailLabelController {

	@Autowired
	private EmailLabelServiceDTO emailLabelServiceDTO;
	
	@PostMapping(value="/addemaillabel/{userId}")
	public EmailLabelDTO addLabels(@RequestBody EmailLabelDTO emailLabelDTO, @PathVariable Integer userId)
	{
		try {
			return emailLabelServiceDTO.createEmailLabel(userId, emailLabelDTO);
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/getemaillabelsbyuserid/{userId}")
	public List<EmailLabelDTO> getEmailLabelsnuUserId(@PathVariable Integer userId) throws Exception
	{
		return emailLabelServiceDTO.getEmailLabelByUserId(userId);
	}
	
	@PostMapping(value="/updateemaillabel/{emailLabelId}")
	public EmailLabelDTO updateEmailLabel(@RequestBody EmailLabelDTO emailLabelDTO, @PathVariable Integer emailLabelId)
	{
		try {
			return emailLabelServiceDTO.updateEmailLabel(emailLabelId, emailLabelDTO) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/getemaillabelbyid/{emailLabelId}")
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
	
	@GetMapping(value="/deleteemaillabelbyid/{emailLabelId}")
	public List<EmailLabelDTO> deleteLabelId(@PathVariable Integer emailLabelId)
	{
		return emailLabelServiceDTO.deleteEmailLabel(emailLabelId);
	}
	
}
