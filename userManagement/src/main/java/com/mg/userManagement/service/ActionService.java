package com.mg.userManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.Action;
import com.mg.userManagement.repo.ActionRepository;

@Service
public class ActionService {

	@Autowired
	private ActionRepository actionRepository;
	
	public Action save(Action action)
	{
		return actionRepository.save(action);
	}
	
	public List<Action> getAll()
	{
		return actionRepository.findAll();
	}
}