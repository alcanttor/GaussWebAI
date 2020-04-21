package com.mg.gateWay.service;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserManagementService {

	@Autowired
	RestTemplate restTemplate;
	public Site getSitebyName(String siteName)
	{
		//restTemplate.
		
	}
	
}
