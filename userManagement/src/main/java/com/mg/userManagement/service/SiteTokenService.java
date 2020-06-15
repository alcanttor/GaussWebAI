package com.mg.userManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.SiteToken;
import com.mg.userManagement.repo.SiteTokenRepository;

/**Underlying service class for all user entity related operations*/
@Service
public class SiteTokenService {

	@Autowired
	private SiteTokenRepository siteTokenRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public SiteToken getSiteTokenFromToken(String token)
	{
		try {
			java.util.Optional<SiteToken> siteTokenOptional = siteTokenRepository.findByToken(token);
			if (siteTokenOptional.isPresent())
			{
				logger.info("token is valid for GaussWebAI");
				return siteTokenOptional.get();
			}
			else
			{
				logger.info("No existing record found for the token");
				logger.error("Token ["+token+"] not valild");
			}
		}
		catch (Exception ex) {
			logger.error("Error stack: ", ex);
		}
		return null;
	}
		
}
