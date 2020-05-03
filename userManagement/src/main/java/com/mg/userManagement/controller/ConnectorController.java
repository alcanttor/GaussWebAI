package com.mg.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.entity.Connector;
import com.mg.userManagement.service.ConnectorService;

@RestController
public class ConnectorController {

	@Autowired
	ConnectorService connectorService;
	
	@PostMapping(value="/addConnector")
	public Connector save(@RequestBody Connector connector)
	{
		return connectorService.save(connector); 
	}
}
