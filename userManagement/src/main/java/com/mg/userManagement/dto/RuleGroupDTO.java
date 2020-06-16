package com.mg.userManagement.dto;

import java.util.Date;
import java.util.List;

public class RuleGroupDTO {
	private Integer id;
	
	List<RuleDTO> rules;
	
	private Boolean enabled;

	private String name;
	
	private String description;
	
	private Date createdDate;
	
	private Date updatedDate;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<RuleDTO> getRules() {
		return rules;
	}

	public void setRules(List<RuleDTO> rules) {
		this.rules = rules;
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
	
		
}
