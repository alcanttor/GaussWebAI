package com.mg.gateWay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

	@Value("${user.management.site}")
	private String siteUrl;

	@Value("${user.management.token}")
	private String tokenUrl;
	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	
	
	public String getTokenUrl() {
		return tokenUrl;
	}

	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}
