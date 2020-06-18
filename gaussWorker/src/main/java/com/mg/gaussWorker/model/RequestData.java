package com.mg.gaussWorker.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

//import com.mg.gateWay.model.Action;
//import com.mg.gateWay.model.SystemEvent;

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
