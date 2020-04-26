package com.mg.gateWay.model;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class Site {

	private Integer id;
	private String name;
	private String connector;
	private User user;
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

	@Override
	public String toString() {
		return "Site [id=" + id + ", name=" + name + ", connector=" + connector + ", user=" + user + ", rules=" + rules
				+ "]";
	}
	
	
}
