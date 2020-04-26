package com.mg.gateWay.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**Model class to map request data beans*/

@Component
public class RequestData {

	private SystemEvent systemEvent;
	private Map<String,String> metaData;
	private String siteName;
	private List<Action> actions;
	
	
	public SystemEvent getSystemEvent() {
		return systemEvent;
	}
	public void setSystemEvent(SystemEvent systemEvent) {
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
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	
}
