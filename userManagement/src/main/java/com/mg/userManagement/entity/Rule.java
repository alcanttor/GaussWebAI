package com.mg.userManagement.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rule {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	private SystemEvent sysTrigger;

	@OneToOne
	private Action action;
	private Boolean enabled;
	
	public Rule(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SystemEvent getSysTrigger() {
		return sysTrigger;
	}
	public void setSysTrigger(SystemEvent sysTrigger) {
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
