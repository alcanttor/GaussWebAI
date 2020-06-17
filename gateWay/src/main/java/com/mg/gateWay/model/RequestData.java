package com.mg.gateWay.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**Model class to map request data beans*/

@Component
public class RequestData {

	private Map<String,String> metaData;
	List<RuleDTO> rules;
	
	public Map<String, String> getMetaData() {
		return metaData;
	}
	public void setMetaData(Map<String, String> metaData) {
		this.metaData = metaData;
	}
	public List<RuleDTO> getRules() {
		return rules;
	}
	public void setRules(List<RuleDTO> rules) {
		this.rules = rules;
	}
	
}
