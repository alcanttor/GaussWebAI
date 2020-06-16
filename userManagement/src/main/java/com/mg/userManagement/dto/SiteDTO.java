package com.mg.userManagement.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

/**DTO mapping class for user site mapping fields*/
public class SiteDTO {
	@Id
	private Integer id;
	
	private Date createdDateTime;
	private Date updatedDateTime;
	
	private String name;
	
	private SiteTokenDTO siteToken;
	
	private ConnectorDTO connector;
	
	private List<RuleGroupDTO> ruleGroups;
	
	public List<RuleGroupDTO> getRuleGroups() {
		return ruleGroups;
	}
	
	public void setRuleGroups(List<RuleGroupDTO> ruleGroups) {
		this.ruleGroups = ruleGroups;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
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
