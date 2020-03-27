package com.mg.gaussWorker.model;

import java.util.Date;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;


public class BaseEvent extends ApplicationEvent{

	public BaseEvent(Object source) {
		super(source);
	}
	
	private Integer id;
	private String name;
	private Date publishedDatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPublishedDatetime() {
		return publishedDatetime;
	}
	public void setPublishedDatetime(Date publishedDatetime) {
		this.publishedDatetime = publishedDatetime;
	}	
	
}

