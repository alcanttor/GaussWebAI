package com.mg.userManagement.dto;

import javax.persistence.Id;

import com.mg.userManagement.entity.SystemRule;

public class RuleDTO {
	@Id
	private Integer id;
	
	SystemRule systemRule;
	
	Boolean enabled;

	private String name;
	private String description;
	private String label;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public EmailTemplateDTO getEmailTemplate() {
		return emailTemplate;
	}
	public void setEmailTemplate(EmailTemplateDTO emailTemplate) {
		this.emailTemplate = emailTemplate;
	}
	
	
}
