package com.mg.gaussWorker.model;

import java.util.HashMap;
import java.util.Map;

public enum ActionList {

	//enum of action names
	SMS ("SMS"),
	EMAIL ("EMAIL");
	
	private String actionDescription;
	
	private static final Map<String, ActionList> lookup = new HashMap<>();
	  
    static
    {
        for(ActionList env : ActionList.values())
        {
            lookup.put(env.getActionDescription(), env);
        }
    }
    
    ActionList(String currentAction){
		this.actionDescription = currentAction;
	}
	
	public String getActionDescription() {
		return actionDescription;
	}
	
	public static ActionList get(String actionEnum) 
    {
        return lookup.get(actionEnum);
    }
}