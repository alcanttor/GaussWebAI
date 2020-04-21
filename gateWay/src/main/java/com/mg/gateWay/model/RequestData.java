package com.mg.gateWay.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**Model class to map request data beans*/

@Component
public class RequestData {

	private List<Actions> actions;
	private Map<String,String> metaData;
	private String siteName;
	
	public List<Actions> getActions() {
		return actions;
	}
	public void setActions(List<Actions> actions) {
		this.actions = actions;
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
	
	
}
