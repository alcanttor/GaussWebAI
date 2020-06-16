package com.mg.userManagement.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

public class EmailTemplateDTO {
	
	@Id
	private Integer id;
	private String name;
	private String template;
	private List<EmailLabelDTO> labels;
	
	private Date createdDateTime;
	private Date updatedDateTime;
	
	private UserDTO user;
	
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
	
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public List<EmailLabelDTO> getLabels() {
		return labels;
	}
	public void setLabels(List<EmailLabelDTO> labels) {
		this.labels = labels;
	}
	
}
