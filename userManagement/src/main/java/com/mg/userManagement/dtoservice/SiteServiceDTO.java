package com.mg.userManagement.dtoservice;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mg.userManagement.dto.RegisterSitesDTO;
import com.mg.userManagement.dto.RuleDTO;
import com.mg.userManagement.dto.SiteListDTO;
import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.entity.Site;
import com.mg.userManagement.service.SiteService;

@Service
public class SiteServiceDTO {

	@Autowired
	private SiteService siteService;
	
	@Autowired
	ModelMapper modelMapper;
		
	public List<SiteListDTO> createSites(List<RegisterSitesDTO> registerSitesDTO, Integer userId){
		@SuppressWarnings("serial")
		Type listTypeSite = new TypeToken<List<Site>>() {}.getType();
		List<Site> allSites = modelMapper.map(registerSitesDTO, listTypeSite);
		
		try {
			siteService.registerSitebyUserId(allSites, userId) ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return this.getSitesbyUser(userId);
	}
	
	public List<SiteListDTO> getSitesbyUser(Integer userId){
		List<Site> allSites = siteService.getSiteByUserId(userId);
		@SuppressWarnings("serial")
		Type listSitesbyUser = new TypeToken<List<SiteListDTO>>() {}.getType();
		return modelMapper.map(allSites, listSitesbyUser);
	}
	
	
	public List<SiteListDTO> deleteSitebyUser(Integer siteId){
		Integer userId = ((siteService.getSiteById(siteId)).getUser()).getId();
		siteService.deleteSiteById(siteId) ;
		return this.getSitesbyUser(userId);
	}
	
	public SiteListDTO updateSitebyUser(SiteListDTO siteListDTO, Integer userID) {
		Site site = modelMapper.map(siteListDTO, Site.class);
		Site updatedSite = siteService.updateSitebyId(site, userID);
		return modelMapper.map(updatedSite, SiteListDTO.class);
	}
	
	public SiteListDTO addRulebySite(Integer siteId, RuleDTO ruleDTO) {
		Rule rule = modelMapper.map(ruleDTO, Rule.class);
		Site site = siteService.addRulebySiteId(siteId, rule);
		return modelMapper.map(site, SiteListDTO.class);
	}
	
	public List<RuleDTO> getAllRulesbyUser(Integer userId){
		List<Rule> allRules = siteService.getAllRulesbyUserID(userId);
		@SuppressWarnings("serial")
		Type listRulesbyUser = new TypeToken<List<RuleDTO>>() {}.getType();
		return modelMapper.map(allRules, listRulesbyUser);
	}
	
	public SiteListDTO assignTemplate(Integer siteId,Integer ruleId, Integer emailTemplateId) {
		try {
			Site site = siteService.asociateTemplate(siteId, ruleId,  emailTemplateId);
			return modelMapper.map(site, SiteListDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
