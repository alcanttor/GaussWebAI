package com.mg.userManagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.SystemEvent;
import com.mg.userManagement.repo.SystemEventRepository;

@Service
public class SystemEventService {

	@Autowired
	private SystemEventRepository systemEventRepository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public SystemEvent save(SystemEvent systemEvent)
	{
		try {
			logger.info("Attempting to save system event: "+systemEvent.getName());
			return systemEventRepository.save(systemEvent);
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid Arguement. Process to save system rule failed: ", ex);
			return null;
		}
		catch (Exception ex) {
			logger.error("Process to save system rule failed: ", ex);
			return null;
		}
	}
	
	public List<SystemEvent> getAll()
	{
		logger.info("Listing all system events");
		return systemEventRepository.findAll();
	}
}
