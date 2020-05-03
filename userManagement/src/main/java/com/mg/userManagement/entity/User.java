package com.mg.userManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="user_seq")
	private Integer id;
	
	private String name;
	private String password;
	private String emailId;
	
//	@OneToMany(mappedBy="user")
//	private List<Site> sites;
	
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
	}/*
	public List<Site> getSites() {
		return sites;
	}
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}*/

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", name=" + name + ", password=" + password + ", emailId=" + emailId +  "]";
	}
	
	
}
