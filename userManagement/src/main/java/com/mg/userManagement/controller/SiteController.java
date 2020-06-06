package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.RegisterSitesDTO;
import com.mg.userManagement.dto.RuleDTO;
import com.mg.userManagement.dto.RuleGroupDTO;
import com.mg.userManagement.dto.SiteDTO;
import com.mg.userManagement.dtoservice.SiteServiceDTO;
@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class SiteController {
	
	@Autowired
	private SiteServiceDTO siteServiceDTO;
	
	
	/*invoke service to generate token and register site(s) for the user*/
	@PostMapping(value="/addsites/{userId}")
	public List<SiteDTO> addSitesbyUser(@RequestBody List<RegisterSitesDTO> registerSitesDTO, @PathVariable Integer userId)
	{
		return siteServiceDTO.createSites(registerSitesDTO, userId);
	}

	@PostMapping(value="/addsite/{userId}")
	public SiteDTO addSitebyUser(@RequestBody RegisterSitesDTO registerSitesDTO, @PathVariable Integer userId)
	{
		return siteServiceDTO.createSite(registerSitesDTO, userId);
	}

	
	/*invoke service to retrieve all site(s) for the user*/
	@GetMapping(value="/getsitebyuserid/{userId}")
	public List<SiteDTO> getSitebyUser(@PathVariable Integer userId)
	{
		return siteServiceDTO.getSitesbyUser(userId);
	}
	
	/*invoke service to delete site by ID*/
	@GetMapping(value="/deletesitebyid/{siteId}")
	public List<SiteDTO> deleteSitebySiteId(@PathVariable Integer siteId)
	{
		return siteServiceDTO.deleteSitebyUser(siteId);
	}
	
	/*invoke service to update selected site*/
	@PostMapping(value="/updatesite/{userId}")
	public SiteDTO updateSite(@RequestBody SiteDTO siteListDTO, @PathVariable Integer userId)
	{
		return siteServiceDTO.updateSitebyUser(siteListDTO, userId);
	}
	
	
	@PostMapping(value="/addruleGroup/{siteId}")
	public SiteDTO addRuleGroup(@PathVariable Integer siteId, @RequestBody RuleGroupDTO ruleGroupDTO)
	{
		return siteServiceDTO.addRuleGroupToSite(siteId, ruleGroupDTO);
	}
	
	/*invoke service to get list of all rules for a given user*/
	@GetMapping(value="/getallruleGroups/{userId}")
	public List<RuleGroupDTO> getAllRules(@PathVariable Integer userId)
	{
		return siteServiceDTO.getAllRuleGroupsbyUser(userId);
	}
	
	/*public SiteDTO asociateTemplate(Integer siteId,Integer ruleId, Integer emailTemplateId)
	{
		return siteServiceDTO.assignTemplate(siteId, ruleId, emailTemplateId);
	}*/
}
