package com.mg.userManagement.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class EmailTemplate {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="emailTemplate_seq")
	private Integer id;
	private String name;
	private String template;
	@OneToMany
	private List<EmailLable> lables;
	
	@CreationTimestamp
	private Date createdDate;
	@UpdateTimestamp
	private Date updatedDate;
	
	@ManyToOne
	private User user;
	@OneToMany
	private List<Rule> rules;
	
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
	public List<EmailLable> getLables() {
		return lables;
	}
	public void setLables(List<EmailLable> lables) {
		this.lables = lables;
	}
	
}
