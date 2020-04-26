package com.mg.gateWay.model;

import org.springframework.stereotype.Component;


@Component
public class Rule {

	private Integer id;
	
	private SystemEvent sysTrigger;
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
