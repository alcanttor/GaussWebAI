package com.mg.gateWay.service;

import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mg.gateWay.config.ApplicationConfiguration;
import com.mg.gateWay.model.tp.Site;

@Service
public class UserManagementService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ApplicationConfiguration applicationConfiguration;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Site getSitebyName(String siteName)
	{
		Site request = new Site();
		request.setName(siteName);
		Site site = restTemplate.postForObject(applicationConfiguration.getSiteUrl(), request, Site.class);
		logger.info("URL [{}] Request [{}] Response [{}]",applicationConfiguration.getSiteUrl(),siteName,site.toString());
		return site;
	}
	
}
