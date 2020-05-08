package com.mg.userManagement.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="user_seq")
	private Integer id;
	
	private String name;
	private String password;
	private String emailId;
	
	@CreationTimestamp
	private Date createdDateTime;
	
	@UpdateTimestamp
	private Date updatedDateTime;
	
	public User(){}
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", name=" + name + ", password=" + password + ", emailId=" + emailId +  "]";
	}
}
