package com.mg.userManagement.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.SystemRule;
import com.mg.userManagement.repo.SystemRuleRepository;

@Service
public class SystemRuleService {

	@Autowired
	private SystemRuleRepository systemRuleRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public SystemRule save(SystemRule systemRule)
	{
		try {
			logger.info("Attempting to create new System rule");
			return systemRuleRepository.save(systemRule);
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid Arguement. Rule creation process failed ", ex);
			return null;
		}
		catch(Exception ex) {
			logger.error("Rule creation process failed ", ex);
			return null;
		}
	}
	
	public List<SystemRule> getAll()
	{
		logger.info("Listing all system rules");
		return systemRuleRepository.findAll();
	}
	
	public SystemRule get(Integer id)
	{
		Optional<SystemRule> srOptional = systemRuleRepository.findById(id);
		if (srOptional.isPresent()) {
			logger.info("System rule found");
			return srOptional.get();
		}
		else { 
			logger.info("System rule not found");
			return null;
		}
	}

	public Object getBySiteId(Integer siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getByConnectorId(Integer connectorId) {
		// TODO Auto-generated method stub
		return null;
	}
}
