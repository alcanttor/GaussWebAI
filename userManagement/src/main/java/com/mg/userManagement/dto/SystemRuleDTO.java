package com.mg.userManagement.dto;

import javax.persistence.Id;

public class SystemRuleDTO {
	@Id
	private Integer id;
	private String descripton;
	private SystemEventDTO systemEvent;
	private ActionDTO action;
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
	public SystemEventDTO getSystemEvent() {
		return systemEvent;
	}
	public void setSystemEvent(SystemEventDTO systemEvent) {
		this.systemEvent = systemEvent;
	}
	public ActionDTO getAction() {
		return action;
	}
	public void setAction(ActionDTO action) {
		this.action = action;
	}
}
