package com.mg.userManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mg.userManagement.entity.Rule;
import com.mg.userManagement.entity.RuleGroup;
import com.mg.userManagement.entity.Site;
import com.mg.userManagement.entity.SiteToken;
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
	
	@Autowired
	private RuleService ruleService ; 
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * Method to check for existing sites, and invoke token assignment as well
	 * as registration of new sites
	 */
	public List<Site> registerSitebyUserId(List<Site> sites, Integer userId) throws Exception {
		User user = userService.getUserById(userId);
		Optional<Site> existingSiteOptional;
		Site existingSite;
		List<Site> savedSite = new ArrayList<>();
		
		for (Site site : sites) {
			existingSiteOptional = siteRepository.getByName(site.getName());
			if (existingSiteOptional.isPresent()) {
				logger.info("Site [{}] already registered. No action required", site.getName());
				existingSite = existingSiteOptional.get();
				savedSite.add(existingSite);
			} else {
				logger.info("Site [{}] registration process starts", site.getName());
				site.setSiteToken(generateToken(site.getName()));			
				site.setUser(user);
				try {
					savedSite.add(siteRepository.save(site));
				}
				catch(IllegalArgumentException ex) {
					logger.error("site registration failed for: "+site.getName());
					logger.error("Invalid arguements in the site entity ", ex);
				}
				catch(Exception ex) {
					logger.error("site registration failed for: "+site.getName());
					logger.error("Exception stack: ", ex);
				}
				logger.info("Site [{}] registered successfully", site.getName());
			}
		}
		return savedSite;
	}

	public Site registerSitebyUserId(Site site, Integer userId) throws Exception {
		User user = userService.getUserById(userId);

		Optional<Site> existingSiteOptional = siteRepository.getByName(site.getName());
		;

		if (existingSiteOptional.isPresent()) {
			logger.info("Site [{}] already registered. No action required.", site.getName());
			throw new Exception("Site already registered");
		} else {
			logger.info("Site [{}] registration process starts", site.getName());

			// Add implementation to get auto-generated token and set it for
			// site entity
			site.setSiteToken(generateToken(site.getName()));
			site.setUser(user);
			try {
				return siteRepository.save(site);
			}catch(IllegalArgumentException ex) {
				logger.error("site registration failed for: "+site.getName());
				logger.error("Invalid arguements in the site entity ", ex);
				return null;
			}
			catch(Exception ex) {
				logger.error("site registration failed for: "+site.getName());
				logger.error("Exception stack: ", ex);
				return null;
			}
		}
	}

	private SiteToken generateToken(String name) {
		SiteToken token = new SiteToken();
		token.setIsValid(true);
		token.setToken(""+System.currentTimeMillis()+name);
		return token;
	}

	/* Method to retrieve existing sites for logged in user */
	public List<Site> getSiteByUserId(Integer userId) {
		logger.info("Retreiving user info for the listing the sites");
		User user = userRepository.findById(userId).get();

		logger.info("Retreiving sites for user [{}]", user.getUsername());
		Optional<List<Site>> sites = siteRepository.getByUser(user);

		if (sites.isPresent()) {
			logger.info("Sites found for the user", user.getUsername());
			return sites.get();
		} else {
			logger.info("No sites found for the user", user.getUsername());
			return null;
		}
	}

	/* Method to retrieve existing sites for logged in user */
	public boolean deleteSiteById(Integer siteId) {
		try {
			logger.info("Attempting to delete site");
			siteRepository.deleteById(siteId);
			logger.info("Deletion success");
			return true;
		} catch (IllegalArgumentException ex) {
			logger.error("Invalid Arguement. Deletion process failed: ", ex);
			return false;
		}
		catch(Exception ex) {
			logger.error("Invalid Arguement. Deletion process failed: ", ex);
			return false;
		}
	}

	/* Method to update existing sites for logged in user */
	public Site updateSitebyId(Site site, Integer userID) {
		logger.info("Attempting to update site [{}]", site.getName());
		Site updatedSite = this.getSiteById(site.getId());

		if (updatedSite != null) {
			updatedSite.setName(site.getName());
			updatedSite.setConnector(site.getConnector());
			updatedSite.setRuleGroups(site.getRuleGroups());
			try {
				updatedSite = siteRepository.save(updatedSite);
			}catch(IllegalArgumentException ex) {
				logger.error("site updation failed for: "+site.getName());
				logger.error("Invalid arguements in the site entity ", ex);
			}
			catch(Exception ex) {
				logger.error("site updation failed for: "+site.getName());
				logger.error("Exception stack: ", ex);
			}
			
			logger.info("Update site to site [{}] successfully", updatedSite.getName());
			return updatedSite;
		} else {
			logger.error("Site [{}] not found. Aborting operation", site.getName());
			return null;
		}
	}

	/* Returns the site for given site id */
	public Site getSiteById(Integer siteId) {
		Optional<Site> site = siteRepository.findById(siteId);
		if (site.isPresent())
			return site.get();
		else
			return null;
	}

	public List<RuleGroup> getAllRuleGroupsbyUserID(Integer userId) {
		logger.info("Retreiving user info for the listing the sites");
		User user = userRepository.findById(userId).get();

		logger.info("Retreiving sites for user [{}]", user.getUsername());
		Optional<List<Site>> sites = siteRepository.getByUser(user);

		List<RuleGroup> globalUserRuleGroups = new ArrayList<RuleGroup>();

		if (sites.isPresent()) {
			logger.info("Sites found for user [{}]", user.getUsername());
			List<Site> sitesList = sites.get();

			for (Site site : sitesList) {
				logger.info("Retreiving rules for site [{}]", site.getName());
				List<RuleGroup> ruleGroups = site.getRuleGroups();

				if (ruleGroups == null) {
					logger.info("No rule groups found for site [{}]", site.getName());
					continue;
				}
				for (RuleGroup ruleGroup : ruleGroups)
					globalUserRuleGroups.add(ruleGroup);
			}

			if (globalUserRuleGroups.isEmpty())
				logger.info("No rules found for user [{}]", user.getUsername());
			else
				logger.info("Rules found for user [{}]", user.getUsername());

			return globalUserRuleGroups;
		} else {
			logger.info("No sites and rules found for user [{}]", user.getUsername());
			return null;
		}
	}
/*
	public Site asociateTemplate(Integer siteId, Integer ruleId, Integer emailTemplateId) throws Exception {
		//Optional<Site> siteOptional = siteRepository.findById(siteId);
		Rule rule = null;
		Site site = getSiteById(siteId);
		EmailTemplate emailTemplate = emailTemplateService.getById(emailTemplateId);
		Optional<Rule> ruleOptional = ruleRepository.findById(ruleId);
		if (ruleOptional.isPresent()) {
			rule = ruleOptional.get();

		} else {
			logger.error("No such rule found");
			throw new Exception("Couldn't find the the rule");
		}
		
		List<Rule> ruleAssociatedWithTemplate = emailTemplate.getRules();
		if (ruleAssociatedWithTemplate == null)
		{
			ruleAssociatedWithTemplate = new ArrayList<>();
		}
		ruleAssociatedWithTemplate.add(rule);
		emailTemplate.setRules(ruleAssociatedWithTemplate);
		try {
			emailTemplateService.update(emailTemplateId,emailTemplate);
		}
		catch(Exception ex) {
			logger.error("Association process for email template failed ", ex);
		}
		return site;
	}*/

	@Transactional
	public Site addRuleGroupToSiteId(Integer siteId, RuleGroup ruleGroup) {
		Optional<Site> siteOptional = siteRepository.findById(siteId);
		Site site = siteOptional.get();

		if (siteOptional.isPresent()) {
			logger.info("Site [{}] found; attempting to add new ruleGroup to it", site.getName());
				
			List<RuleGroup> ruleGroups = site.getRuleGroups();
			if (ruleGroups == null) {
				ruleGroups = new ArrayList<>();
			}
			
			List<Rule> savedRules = saveRules(ruleGroup.getRules());
			ruleGroup.setRules(savedRules);
			ruleGroups.add(ruleGroup);
			site.setRuleGroups(ruleGroups);
			logger.info("New ruleGroup added for the site: [{}]", site.getName());

			try {
				return siteRepository.save(site);
			}

			catch (Exception ex) {
				logger.error("Save failed while trying to add new rulegroup for the site: ", ex);
				return null;
			}
		} else {
			logger.error("Site [{}] not found", site.getName());
			return null;
		}

	}

private List<Rule> saveRules(List<Rule> rules) {
	List<Rule> savedRules = new ArrayList<>();
	for(Rule rule : rules)
	{
		try {
			savedRules.add(ruleService.mergeRule(rule));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return savedRules;
}

public Site getSitebyNameandToken(String siteName, String token)
{
	return siteRepository.getByNameAndToken(siteName, token);
}

}