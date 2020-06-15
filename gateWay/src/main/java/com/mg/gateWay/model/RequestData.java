package com.mg.gateWay.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**Model class to map request data beans*/

@Component
public class RequestData {

	private SystemEventDTO systemEvent;
	private Map<String,String> metaData;
	private String siteName;
	List<RuleDTO> rules;
	
	public SystemEventDTO getSystemEvent() {
		return systemEvent;
	}
	public void setSystemEvent(SystemEventDTO systemEvent) {
		this.systemEvent = systemEvent;
	}
	public Map<String, String> getMetaData() {
		return metaData;
	}
	public void setMetaData(Map<String, String> metaData) {
		this.metaData = metaData;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public List<RuleDTO> getRules() {
		return rules;
	}
	public void setRules(List<RuleDTO> rules) {
		this.rules = rules;
	}
	
}
