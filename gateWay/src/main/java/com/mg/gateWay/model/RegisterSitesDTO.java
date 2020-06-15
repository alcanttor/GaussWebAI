package com.mg.gateWay.model;

/**DTO mapping class for registering sites*/
public class RegisterSitesDTO {
	
	private Integer id;
	
	private String name;
	private ConnectorDTO connector;

	private UserDTO user;

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

	public ConnectorDTO getConnector() {
		return connector;
	}

	public void setConnector(ConnectorDTO connector) {
		this.connector = connector;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}
