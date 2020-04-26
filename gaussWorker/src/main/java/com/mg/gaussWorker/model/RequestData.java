package com.mg.gaussWorker.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

//import com.mg.gateWay.model.Action;
//import com.mg.gateWay.model.SystemEvent;

@Component
public class RequestData {

	//private SystemEvent systemEvent;
	private Map<String,String> metaData;
	private String siteName;
	private List<Action> actions;
	
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	public Map<String, String> getMetaData() {
		return metaData;
	}
	public void setMetaData(Map<String, String> metaData) {
		this.metaData = metaData;
	}
	
	
}
