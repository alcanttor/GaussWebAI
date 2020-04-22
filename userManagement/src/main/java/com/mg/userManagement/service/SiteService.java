package com.mg.userManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

	public Site registerSite(Site site, Integer userId) {

		User user = userService.getUserById(userId);
		List<Rule> rules = ruleService.saveList(site.getRules());
		site.setUser(user);
		site.setRules(rules);
		return siteRepository.save(site);
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
	
	@Transactional
	public Site addRule(Integer siteId,Rule rule)
	{
		Site site = siteRepository.findById(siteId).get();
		System.out.println("Site is found......");
		List<Rule> rules = site.getRules();
		System.out.println("rules of site : "+rules);
		if(rules == null)
		{
			rules = new ArrayList<>();
		}
		rules.add(ruleService.save(rule));
		System.out.println("new rule added to list");
		try{
		return siteRepository.save(site);
		}
		catch(Exception e){e.printStackTrace();
		return null;}
	}
	
	public List<Site> getAll()
	{
		return siteRepository.findAll();
	}
}
