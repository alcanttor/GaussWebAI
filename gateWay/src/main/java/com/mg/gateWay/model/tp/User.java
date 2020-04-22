package com.mg.gateWay.model.tp;

import org.springframework.stereotype.Component;

@Component
public class User {

	private Integer id;
	
	private String name;
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
		return "SysUser [id=" + id + ", name=" + name + ", emailId=" + emailId +  "]";
	}
	
	
}
