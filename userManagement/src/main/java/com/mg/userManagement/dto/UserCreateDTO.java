package com.mg.userManagement.dto;

import javax.persistence.Id;

public class UserCreateDTO {
	
	@Id
	private Integer id;
	
	private String emailId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
