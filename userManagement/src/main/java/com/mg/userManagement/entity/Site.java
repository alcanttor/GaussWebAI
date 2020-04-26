package com.mg.userManagement.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Site {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String connector;
	@ManyToOne
	private User user;
	@OneToOne
	private SiteToken siteToken;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Rule> rules;
	
	public Site(){}
	
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
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Rule> getRules() {
		return rules;
	}
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public SiteToken getSiteToken() {
		return siteToken;
	}

	public void setSiteToken(SiteToken siteToken) {
		this.siteToken = siteToken;
	}
	
	
}
