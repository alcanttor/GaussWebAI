package com.mg.userManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Action extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="action_seq")
	private Integer id;
	private String name;
	private Boolean onEmail;
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
	public Boolean getOnEmail() {
		return onEmail;
	}
	public void setOnEmail(Boolean onEmail) {
		this.onEmail = onEmail;
	}
	
	
}
