package com.mg.gaussWorker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import com.mg.gaussWorker.model.BaseEvent;

@Service
public class EventPublisherService implements ApplicationEventPublisherAware {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher)
	{
		this.publisher = publisher;
	}
	
	public void publishEvent(BaseEvent event)
	{
		publisher.publishEvent(event);
	}
	
}
