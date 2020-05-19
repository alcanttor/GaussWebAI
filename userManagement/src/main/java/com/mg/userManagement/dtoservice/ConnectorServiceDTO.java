package com.mg.userManagement.dtoservice;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mg.userManagement.dto.ConnectorDTO;
import com.mg.userManagement.entity.Connector;
import com.mg.userManagement.service.ConnectorService;

@Service
public class ConnectorServiceDTO {

	@Autowired
	private ConnectorService connectorService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ConnectorDTO saveConnector(ConnectorDTO connectorDTO)
	{
		Connector connector = modelMapper.map(connectorDTO, Connector.class);
		return modelMapper.map(connectorService.save(connector), ConnectorDTO.class);
	}
	
	public List<ConnectorDTO> getAllConnectors()
	{
		@SuppressWarnings("serial")
		Type listAllConnectors = new TypeToken<List<ConnectorDTO>>(){}.getType();
		return modelMapper.map(connectorService.getAll(), listAllConnectors);
	}
}
