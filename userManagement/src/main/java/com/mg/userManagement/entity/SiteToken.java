package com.mg.userManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SiteToken extends BaseEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="siteToken_seq")
	private Integer id;
	
	@Column(unique=true)
	private String token;
	private Boolean isValid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}	
	
}
