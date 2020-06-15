package com.mg.gateWay.model;

import java.util.Date;
import java.util.List;

public class EmailTemplateDTO {
	
	private Integer id;
	private String name;
	private String template;
	private List<EmailLabelDTO> labels;
	
	private Date createdDate;
	private Date updatedDate;
	
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
	
	public List<EmailLabelDTO> getLabels() {
		return labels;
	}
	public void setLabels(List<EmailLabelDTO> labels) {
		this.labels = labels;
	}
	
}
