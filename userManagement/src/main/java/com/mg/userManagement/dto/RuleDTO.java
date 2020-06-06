package com.mg.userManagement.dto;

import javax.persistence.Id;

import com.mg.userManagement.entity.SystemRule;

public class RuleDTO {
	@Id
	private Integer id;
	
	SystemRule systemRule;
	
	Boolean enabled;
	
	private EmailTemplateDTO emailTemplate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SystemRule getSystemRule() {
		return systemRule;
	}
	public void setSystemRule(SystemRule systemRule) {
		this.systemRule = systemRule;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public EmailTemplateDTO getEmailTemplate() {
		return emailTemplate;
	}
	public void setEmailTemplate(EmailTemplateDTO emailTemplate) {
		this.emailTemplate = emailTemplate;
	}
	
	
}
