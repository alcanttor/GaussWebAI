package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.model.Rule;
import com.mg.userManagement.model.Site;
import com.mg.userManagement.service.SiteService;

@RestController
public class SiteController {

	@Autowired
	private SiteService siteService;
	
	@PostMapping(value="/AddSite/{userId}")
	public Site addSite(@RequestBody Site site, @PathVariable Integer userId)
	{
		return siteService.registerSite(site, userId) ;
	}
	
	@PostMapping(value="/AddRule/{siteId}")
	public Site addRule(@PathVariable Integer siteId, @RequestBody Rule rule)
	{
		return siteService.addRule(siteId, rule);
	}
	
	@GetMapping(value="/getAll")
	public List<Site> getAll()
	{
		return siteService.getAll();
	}
}
