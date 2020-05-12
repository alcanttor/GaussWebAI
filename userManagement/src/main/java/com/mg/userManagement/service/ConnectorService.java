package com.mg.userManagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.Connector;
import com.mg.userManagement.repo.ConnectorRepository;

@Service
public class ConnectorService {

	@Autowired
	private ConnectorRepository connectorRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Connector save(Connector connector)
	{
		try {
			logger.info("Attempting to save new connector: "+connector.getName());
			return connectorRepository.save(connector);
		}
		catch(IllegalArgumentException ex) {
			logger.error("Invalid arguements passed while trying to save connector: "+connector.getName());
			logger.error("", ex);
		}
		catch(Exception ex)
		{
			logger.error("Exception while trying to save connector: "+connector.getName());
			logger.error("", ex);
		}
		return null;
	}
	
	public List<Connector> getAll()
	{
		logger.info("Listing all connectors");
		return connectorRepository.findAll();
	}
}