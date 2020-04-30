package com.mg.userManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.entity.Site;
import com.mg.userManagement.entity.User;
import com.mg.userManagement.repo.SiteRepository;
import com.mg.userManagement.repo.UserRepository;

@Service
public class SiteService {

	@Autowired
	private UserService userService;
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private UserRepository userRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*Method to check for existing sites, and invoke token assignment as well as registration of new sites*/
	public List<Site> registerSite(List<Site> sites, Integer userId)
	{
		User user = userService.getUserById(userId);
		Optional<Site> existingSiteOptional;
		Site existingSite;
		List<Site> savedSite = new ArrayList<>();
		
		for(Site site: sites)
		{
			existingSiteOptional = siteRepository.getByName(site.getName());
			if (existingSiteOptional.isPresent())
			{
				logger.info("Site [{}] already registered. No action required.",site.getName());
				existingSite = existingSiteOptional.get();
				savedSite.add(existingSite);
			}
			else
			{
				logger.info("Site [{}] registration process starts", site.getName());
				
				//Add implementation to get auto-generated token and set it for site entity
				
				site.setUser(user);
				savedSite.add(siteRepository.save(site));
				logger.info("Site [{}] registered successfully", site.getName());
			}
		}
		return savedSite;
	}
	
	/*Method to retrieve existing sites for logged in user*/
	public List<Site> getSiteByUser(Integer userId) {
		logger.info("Retreiving user info for the listing the sites");
		User user = userRepository.findById(userId).get();
		
		logger.info("Retreiving sites for user [{}]", user.getName());
		Optional <List<Site>> site = siteRepository.getByUser(user);
		
		if (site.isPresent()) {
			logger.info("Sites found for the user", user.getName());
			return site.get();
		}
		else {
			logger.info("No sites found for the user", user.getName());
			return null;		
		}
	}
	
	
	/*Method to retrieve existing sites for logged in user*/
	public boolean deleteSiteById(Integer siteId) {
		try {
			logger.info("Attempting to delete site");
			siteRepository.deleteById(siteId);
			logger.info("Deletion success");
			return true;
		}
		catch(IllegalArgumentException ex) {
			logger.error("Deletion process failed: ",ex);
			return false;
		}
	}
	
	/*Method to update existing sites for logged in user*/
	public Site updateSite(Site site, Integer userID) {
			logger.info("Attempting to update site [{}]", site.getName());
			Site updatedSite = this.getSiteById(site.getId());
			
			if(updatedSite!=null) {
				updatedSite.setName(site.getName());
				return siteRepository.save(updatedSite);
			}
			else {
				logger.error("Site [{}] not found. Aborting operation", site.getName());
				return null;
			}
	}
	
	
	/*Returns the site for given site id*/
	public Site getSiteById(Integer siteId) {
		Optional<Site> site = siteRepository.findById(siteId);
		if (site.isPresent())
			return site.get();
		else
			return null;
	}

	/*Returns the site for given site id*/
	public Site getSiteByName(String siteName) {
		Optional<Site> site = siteRepository.getByName(siteName);
		if (site.isPresent())
			return site.get();
		else
			return null;
	}
	
	public Site addRule(Integer siteId,Rule rule)
	{
		Optional<Site> siteOptional = siteRepository.findById(siteId);
		if (siteOptional.isPresent())
		{
			Site site = siteOptional.get();
			System.out.println("Site is found......");
			List<Rule> rules = site.getRules();
			System.out.println("rules of site : "+rules);
			if(rules == null)
			{
				rules = new ArrayList<>();
			}
			rules.add(rule);
			System.out.println("new rule added to list");
			try
			{
				return siteRepository.save(site);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		else
		{
			logger.info("Site is not present in DB");
			return null;
		}
	}
	
	public List<Site> getAll()
	{
		return siteRepository.findAll();
	}

}
