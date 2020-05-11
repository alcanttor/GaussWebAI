package com.mg.userManagement.dto;

import java.util.Date;
import javax.persistence.Id;

/**DTO mapping class for user site mapping fields*/
public class SiteListDTO {
	@Id
	private Integer id;
	
	private Date createdDate;
	private Date updatedDate;
	
	private String name;
	
	private SiteTokenDTO siteToken;
	
	private ConnectorDTO connector;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SiteTokenDTO getSiteToken() {
		return siteToken;
	}
	public void setSiteToken(SiteTokenDTO siteToken) {
		this.siteToken = siteToken;
	}
	public ConnectorDTO getConnector() {
		return connector;
	}
	public void setConnector(ConnectorDTO connector) {
		this.connector = connector;
	}
	
}
