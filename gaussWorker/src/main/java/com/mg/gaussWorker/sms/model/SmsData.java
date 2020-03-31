package com.mg.gaussWorker.sms.model;

import com.mg.gaussWorker.model.BaseEvent;

public class SmsData extends BaseEvent{

	public SmsData(Object source) {
		super(source);
	}

	private String to;
	private String from;
	private String text ;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "SmsData [to=" + to + ", from=" + from + ", text=" + text + "]";
	}
	
	
}
