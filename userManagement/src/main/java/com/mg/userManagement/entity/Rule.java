package com.mg.userManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rule {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="rule_seq")
	private Integer id;
	
	@OneToOne
	SystemRule systemRule;
	
	Boolean enabled;

	private String name;
	private String description;
	private String label;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
