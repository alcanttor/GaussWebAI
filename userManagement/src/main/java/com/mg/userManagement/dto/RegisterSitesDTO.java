package com.mg.userManagement.dto;

import javax.persistence.Id;
import com.mg.userManagement.entity.Connector;
import com.mg.userManagement.entity.User;

/**DTO mapping class for registering sites*/
public class RegisterSitesDTO {
	
	
	@Id
	private Integer id;
	
	private String name;
	private Connector connector;

	private User user;

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

	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
