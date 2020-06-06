package com.mg.userManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rule extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="rule_seq")
	private Integer id;
	
	@OneToOne
	SystemRule systemRule;
	
	private Boolean enabled;
	
	@OneToOne
	private EmailTemplate emailTemplate;
	
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

	public EmailTemplate getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(EmailTemplate emailTemplate) {
		this.emailTemplate = emailTemplate;
	}
	
}
