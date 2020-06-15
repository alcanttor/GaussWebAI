package com.mg.userManagement.dto;

import javax.persistence.Id;

public class RuleDTO {
	@Id
	private Integer id;
	
	SystemRuleDTO systemRule;
	
	Boolean enabled;
	
	private EmailTemplateDTO emailTemplate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SystemRuleDTO getSystemRule() {
		return systemRule;
	}
	public void setSystemRule(SystemRuleDTO systemRule) {
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
