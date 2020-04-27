package com.mg.userManagement.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SystemRule {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@OneToOne
	private SystemEvent systemEvent;
	@OneToOne
	private Action action;
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
	public SystemEvent getSystemEvent() {
		return systemEvent;
	}
	public void setSystemEvent(SystemEvent systemEvent) {
		this.systemEvent = systemEvent;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
}
