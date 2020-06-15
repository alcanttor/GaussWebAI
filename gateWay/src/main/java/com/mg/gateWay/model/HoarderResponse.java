package com.mg.gateWay.model;

import org.springframework.stereotype.Component;

@Component
public class HoarderResponse {

	private Boolean result;
	private String message;
	
	public HoarderResponse(){}
	
	public HoarderResponse(Boolean result,String message)
	{
		this.result = result;
		this.message = message;
	}
	
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
