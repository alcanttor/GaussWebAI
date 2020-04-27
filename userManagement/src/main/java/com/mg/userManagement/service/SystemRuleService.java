package com.mg.userManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.Action;
import com.mg.userManagement.entity.SystemRule;
import com.mg.userManagement.repo.ActionRepository;
import com.mg.userManagement.repo.SystemRuleRepository;

@Service
public class SystemRuleService {

	@Autowired
	private SystemRuleRepository systemRuleRepository;
	
	public SystemRule save(SystemRule systemRule)
	{
		return systemRuleRepository.save(systemRule);
	}
	
	public List<SystemRule> getAll()
	{
		return systemRuleRepository.findAll();
	}
	
	public SystemRule get(Integer id)
	{
		Optional<SystemRule> srOptional = systemRuleRepository.findById(id);
		if (srOptional.isPresent())
			return srOptional.get();
		else 
			return null;
		
	}
}
