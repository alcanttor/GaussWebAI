package com.mg.userManagement.dto;

import javax.persistence.Id;

/**DTO mapping class for connector mapping fields*/
public class ConnectorDTO {
	@Id
	private Integer id;
	private String name;
	
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
}
