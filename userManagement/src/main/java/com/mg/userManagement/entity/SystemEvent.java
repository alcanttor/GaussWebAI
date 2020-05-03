package com.mg.userManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemEvent {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="systemEvent_seq")
	private Integer id;
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return description;
	}
	public void setName(String name) {
		this.description = name;
	}
	
	
}
