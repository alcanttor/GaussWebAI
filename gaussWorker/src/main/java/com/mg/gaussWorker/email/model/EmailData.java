package com.mg.gaussWorker.email.model;

import com.mg.gaussWorker.model.BaseEvent;


/**Bean class for email fields
 * 
 */

public class EmailData extends BaseEvent{

	private static final long serialVersionUID = 1L;
	
	public EmailData(Object source) {
		super(source);
	}
	
	private String to;
	private String subject;
	private String text;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
