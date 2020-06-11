package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.ConnectorDTO;
import com.mg.userManagement.dtoservice.ConnectorServiceDTO;

@RestController
public class ConnectorController {

	@Autowired
	ConnectorServiceDTO connectorServiceDTO;
	
	@PostMapping(value="/addConnector")
	public ConnectorDTO saveConnector(@RequestBody ConnectorDTO connectorDTO)
	{
		return connectorServiceDTO.saveConnector(connectorDTO); 
	}
	
	@GetMapping(value="/listconnectors")
	public List<ConnectorDTO> listConnectors(){
		return connectorServiceDTO.getAllConnectors();
	}
}
