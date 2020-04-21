package com.mg.userManagement.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rule {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Enumerated
	private SystemTrigger sysTrigger;
	@Enumerated
	private Action action;
	private Boolean enabled;
	
	public Rule(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SystemTrigger getSysTrigger() {
		return sysTrigger;
	}
	public void setSysTrigger(SystemTrigger sysTrigger) {
		this.sysTrigger = sysTrigger;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
}
