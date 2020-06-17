package com.mg.gateWay.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mg.gateWay.config.ApplicationConfiguration;
import com.mg.gateWay.model.AuthorizationTokenResponse;
import com.mg.gateWay.model.SiteDTO;

@Service
public class UserManagementService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ApplicationConfiguration applicationConfiguration;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public SiteDTO getSitebyName(String siteName, String siteToken)
	{
		String jwt = getToken(siteName, siteToken);
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("siteToken", siteToken);
	    params.put("siteName", siteName);
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", jwt);
	    HttpEntity entity = new HttpEntity(headers);
	    ResponseEntity<SiteDTO> site = restTemplate.exchange(applicationConfiguration.getSiteUrl(),HttpMethod.GET,entity,SiteDTO.class,params);
		logger.info("URL [{}] Request [{}] Response [{}]",applicationConfiguration.getSiteUrl(),siteName,site);
		return site.getBody();
	}
	
	private String getToken(String siteName, String siteToken)
	{
		AuthorizationTokenResponse response = new AuthorizationTokenResponse();
		Map<String, String> params = new HashMap<String, String>();
	    params.put("siteToken", siteToken);
	    params.put("siteName", siteName);
	    RestTemplate restTemplate = new RestTemplate();
	    response = restTemplate.getForObject(applicationConfiguration.getTokenUrl(), AuthorizationTokenResponse.class, params);
	    return response.getJwt();
	}
}