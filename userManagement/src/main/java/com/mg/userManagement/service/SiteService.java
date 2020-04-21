package com.mg.userManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.model.Rule;
import com.mg.userManagement.model.Site;
import com.mg.userManagement.model.SysUser;
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

		SysUser user = userService.getUserById(userId);
		site.setUser(user);
		return siteRepository.save(site);
	}

	public Site getSite(Integer siteId) {
		Optional<Site> site = siteRepository.findById(siteId);
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
