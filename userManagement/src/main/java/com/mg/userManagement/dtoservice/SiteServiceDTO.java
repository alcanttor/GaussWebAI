package com.mg.userManagement.dtoservice;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mg.userManagement.dto.RegisterSitesDTO;
import com.mg.userManagement.dto.RuleDTO;
import com.mg.userManagement.dto.SiteDTO;
import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.entity.Site;
import com.mg.userManagement.service.SiteService;

@Service
public class SiteServiceDTO {

	@Autowired
	private SiteService siteService;
	
	@Autowired
	ModelMapper modelMapper;
		
	public List<SiteDTO> createSites(List<RegisterSitesDTO> registerSitesDTO, Integer userId){
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

	public SiteDTO createSite(RegisterSitesDTO registerSitesDTO, Integer userId){
		Site site = new Site();
		SiteDTO siteDto = new SiteDTO();
		modelMapper.map(registerSitesDTO, site);
		Site siteSaved = null;
		try {
			siteSaved = siteService.registerSitebyUserId(site,userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		modelMapper.map(siteSaved, siteDto);
		return siteDto;
	}

	public List<SiteDTO> getSitesbyUser(Integer userId){
		List<Site> allSites = siteService.getSiteByUserId(userId);
		@SuppressWarnings("serial")
		Type listSitesbyUser = new TypeToken<List<SiteDTO>>() {}.getType();
		return modelMapper.map(allSites, listSitesbyUser);
	}
	
	
	public List<SiteDTO> deleteSitebyUser(Integer siteId){
		Integer userId = ((siteService.getSiteById(siteId)).getUser()).getId();
		siteService.deleteSiteById(siteId) ;
		return this.getSitesbyUser(userId);
	}
	
	public SiteDTO updateSitebyUser(SiteDTO siteListDTO, Integer userID) {
		Site site = modelMapper.map(siteListDTO, Site.class);
		Site updatedSite = siteService.updateSitebyId(site, userID);
		return modelMapper.map(updatedSite, SiteDTO.class);
	}
	
	public SiteDTO addRulebySite(Integer siteId, RuleDTO ruleDTO) {
		Rule rule = modelMapper.map(ruleDTO, Rule.class);
		Site site = siteService.addRulebySiteId(siteId, rule);
		return modelMapper.map(site, SiteDTO.class);
	}
	
	public List<RuleDTO> getAllRulesbyUser(Integer userId){
		List<Rule> allRules = siteService.getAllRulesbyUserID(userId);
		@SuppressWarnings("serial")
		Type listRulesbyUser = new TypeToken<List<RuleDTO>>() {}.getType();
		return modelMapper.map(allRules, listRulesbyUser);
	}
	
	public SiteDTO assignTemplate(Integer siteId,Integer ruleId, Integer emailTemplateId) {
		try {
			Site site = siteService.asociateTemplate(siteId, ruleId,  emailTemplateId);
			return modelMapper.map(site, SiteDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
