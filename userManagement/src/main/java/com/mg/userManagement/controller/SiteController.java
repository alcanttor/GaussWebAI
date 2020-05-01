package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.entity.Site;
import com.mg.userManagement.service.SiteService;

@RestController
public class SiteController {

	@Autowired
	private SiteService siteService;
	
	
	/*invoke service to generate token and register site(s) for the user*/
	@PostMapping(value="/addsite/{userId}")
	public List<Site> addSitesbyUser(@RequestBody List<Site> sites, @PathVariable Integer userId)
	{
		return siteService.registerSitebyUserId(sites, userId) ;
	}
	
	/*invoke service to retrieve all site(s) for the user*/
	@GetMapping(value="/getsitebyuserid/{userId}")
	public List<Site> getSitebyUser(@PathVariable Integer userId)
	{
		return siteService.getSiteByUserId(userId) ;
	}
	
	/*invoke service to delete site by ID*/
	@GetMapping(value="/deletesitebyid/{siteId}")
	public boolean deleteSitebySiteId(@PathVariable Integer siteId)
	{
		return siteService.deleteSiteById(siteId) ;
	}
	
	/*invoke service to update selected site*/
	@PostMapping(value="/updatesite/{userId}")
	public Site updateSite(@RequestBody Site site, @PathVariable Integer userId)
	{
		return siteService.updateSitebyId(site, userId) ;
	}
	
	/*invoke service to add new rule to the site*/
	@PostMapping(value="/addrule/{siteId}")
	public Site addRule(@PathVariable Integer siteId, @RequestBody Rule rule)
	{
		return siteService.addRulebySiteId(siteId, rule);
	}
	
	/*invoke service to get list of all rules for a given user*/
	@PostMapping(value="/getallrules/{userId}")
	public List<Rule> getAllRules(@PathVariable Integer userId)
	{
		return siteService.getAllRulesbyUserID(userId);
	}
}
