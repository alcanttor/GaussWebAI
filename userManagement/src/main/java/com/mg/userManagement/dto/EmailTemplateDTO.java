package com.mg.userManagement.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

public class EmailTemplateDTO {
	
	@Id
	private Integer id;
	private String name;
	private String template;
	private String label;
	
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
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
}
