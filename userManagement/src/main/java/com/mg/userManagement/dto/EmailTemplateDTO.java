package com.mg.userManagement.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

public class EmailTemplateDTO {
	
	@Id
	private Integer id;
	private String name;
	private String template;
	private List<EmailLableDTO> lables;
	
	private Date createdDate;
	private Date updatedDate;
	
	private UserDTO user;
	private List<RuleDTO> rules;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public List<RuleDTO> getRules() {
		return rules;
	}
	public void setRules(List<RuleDTO> rules) {
		this.rules = rules;
	}
	public List<EmailLableDTO> getLables() {
		return lables;
	}
	public void setLables(List<EmailLableDTO> lables) {
		this.lables = lables;
	}
	
}
