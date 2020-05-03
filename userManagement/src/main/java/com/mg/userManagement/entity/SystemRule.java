package com.mg.userManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SystemRule {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="systemRule_seq")
	private Integer id;
	private String descripton;
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
	
	public String getDescripton() {
		return descripton;
	}
	public void setDescripton(String descripton) {
		this.descripton = descripton;
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
