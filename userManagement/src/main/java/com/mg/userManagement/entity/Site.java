package com.mg.userManagement.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Site extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="site_seq")
	private Integer id;
	
	@Column(unique = true)
	private String name;
	
	@OneToOne
	private Connector connector;
	@ManyToOne
	private User user;
	@OneToOne(cascade=CascadeType.ALL)
	private SiteToken siteToken;
	@OneToMany(cascade=CascadeType.ALL)
	private List<RuleGroup> ruleGroups;
	
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
	public Connector getConnector() {
		return connector;
	}
	public void setConnector(Connector connector) {
		this.connector = connector;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public List<RuleGroup> getRuleGroups() {
		return ruleGroups;
	}

	public void setRuleGroups(List<RuleGroup> ruleGroups) {
		this.ruleGroups = ruleGroups;
	}

	public SiteToken getSiteToken() {
		return siteToken;
	}

	public void setSiteToken(SiteToken siteToken) {
		this.siteToken = siteToken;
	}
	
}
