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
	public List<Site> addSite(@RequestBody List<Site> sites, @PathVariable Integer userId)
	{
		return siteService.registerSite(sites, userId) ;
	}
	
	/*invoke service to retrieve all site(s) for the user*/
	@GetMapping(value="/getsitebyuserid/{userId}")
	public List<Site> getSitebyUserId(@PathVariable Integer userId)
	{
		return siteService.getSiteByUser(userId) ;
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
		return siteService.updateSite(site, userId) ;
	}
	
	@PostMapping(value="/addrule/{siteId}")
	public Site addRule(@PathVariable Integer siteId, @RequestBody Rule rule)
	{
		return siteService.addRule(siteId, rule);
	}
	
	@GetMapping(value="/getall")
	public List<Site> getAll()
	{
		return siteService.getAll();
	}
	
	@PostMapping("/getsite")
	public Site getSitebyName(@RequestBody Site site)
	{
		return siteService.getSiteByName(site.getName());
	}
	
}
