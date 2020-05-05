package com.mg.userManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.SystemEvent;
import com.mg.userManagement.repo.SystemEventRepository;

@Service
public class SystemEventService {

	@Autowired
	private SystemEventRepository systemEventRepository;
	
	public SystemEvent save(SystemEvent systemEvent)
	{
		return systemEventRepository.save(systemEvent);
	}
	
	public List<SystemEvent> getAll()
	{
		return systemEventRepository.findAll();
	}
}
