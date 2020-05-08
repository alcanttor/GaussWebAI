package com.mg.userManagement.dto;

import java.util.Date;
import javax.persistence.Id;

import com.mg.userManagement.entity.Connector;
import com.mg.userManagement.entity.SiteToken;

public class SiteListDTO {
	@Id
	private Integer id;
	
	private Date createdDate;
	private Date updatedDate;
	
	private String name;
	
	private SiteToken siteToken;
	
	private Connector connector;
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
	public Connector getConnector() {
		return connector;
	}
	public void setConnector(Connector connector) {
		this.connector = connector;
	}
	public SiteToken getSiteToken() {
		return siteToken;
	}
	public void setSiteToken(SiteToken siteToken) {
		this.siteToken = siteToken;
	}
	
}
