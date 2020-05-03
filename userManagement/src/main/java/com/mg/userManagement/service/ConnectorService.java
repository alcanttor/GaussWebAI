package com.mg.userManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.Action;
import com.mg.userManagement.entity.Connector;
import com.mg.userManagement.repo.ActionRepository;
import com.mg.userManagement.repo.ConnectorRepository;

@Service
public class ConnectorService {

	@Autowired
	private ConnectorRepository connectorRepository;
	
	public Connector save(Connector connector)
	{
		return connectorRepository.save(connector);
	}
	
	public List<Connector> getAll()
	{
		return connectorRepository.findAll();
	}
}