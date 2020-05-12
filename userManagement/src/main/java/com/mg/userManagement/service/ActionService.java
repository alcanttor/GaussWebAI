package com.mg.userManagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.Action;
import com.mg.userManagement.repo.ActionRepository;

@Service
public class ActionService {

	@Autowired
	private ActionRepository actionRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Action save(Action action)
	{
		try {
			logger.info("Attempting to save new action: "+action.getName());
			return actionRepository.save(action);
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid arguements passed while trying to save action: "+action.getName());
			logger.error("", ex);
		}
		catch(Exception ex)
		{
			logger.error("Exception while trying to save action: "+action.getName());
			logger.error("", ex);
		}
		return null;
	}
	
	public List<Action> getAll()
	{
		logger.info("Listing all actions");
		return actionRepository.findAll();
	}
}