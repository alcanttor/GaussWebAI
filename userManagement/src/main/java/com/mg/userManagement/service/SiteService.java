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
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	@Autowired
	private SiteRepository siteRepository;
	
	@Autowired
	private RuleService ruleService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Site registerSite(Site site, Integer userId) {

		User user = userService.getUserById(userId);
		Optional<Site> existingSiteOptional = siteRepository.getByName(site.getName());
		if (existingSiteOptional.isPresent())
		{
			logger.info("Site [{}] already in DB, update it",site.getName());
			Site existingSite = existingSiteOptional.get();
			//existingSite.getRules().add(site.getRules().get(0));
			//List<Rule> rules = ruleService.saveList(site.getRules());
			//existingSite.setRules(rules);
			return existingSite;
		}
		else
		{
			logger.info("Site [{}] not in DB, create it",site.getName());
			List<Rule> rules = ruleService.saveList(site.getRules());
			site.setUser(user);
			site.setRules(rules);
			return siteRepository.save(site);
		}
	}

	public Site getSiteById(Integer siteId) {
		Optional<Site> site = siteRepository.findById(siteId);
		if (site.isPresent())
			return site.get();
		else
			return null;
	}

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
