package com.mg.userManagement.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class EmailTemplate extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="emailTemplate_seq")
	private Integer id;
	
	private String name;
	
	private String template;
	@ManyToMany
	private List<EmailLabel> labels;
	
	@ManyToOne
	private User user;
	
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
	
	public List<EmailLabel> getLabels() {
		return labels;
	}
	public void setLabels(List<EmailLabel> labels) {
		this.labels = labels;
	}
	
}
