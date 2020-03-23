package com.mg.eventConsumer.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Event {

	private Integer id;
	private String name;
	private Date publishedDatetime;
	private Object eventData;
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
	public Object getEventData() {
		return eventData;
	}
	public void setEventData(Object eventData) {
		this.eventData = eventData;
	}
	
	
}

