package com.mg.userManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SystemRule extends BaseEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="systemRule_seq")
	private Integer id;
	private String description;
	@OneToOne
	private SystemEvent systemEvent;
	@OneToOne
	private Action action;
	@OneToOne
	private Connector connector;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Connector getConnector() {
		return connector;
	}
	public void setConnector(Connector connector) {
		this.connector = connector;
	}
	
}
