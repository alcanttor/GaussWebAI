package com.mg.userManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rule {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	SystemRule systemRule;
	
	Boolean enabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SystemRule getSystemRule() {
		return systemRule;
	}

	public void setSystemRule(SystemRule systemRule) {
		this.systemRule = systemRule;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
