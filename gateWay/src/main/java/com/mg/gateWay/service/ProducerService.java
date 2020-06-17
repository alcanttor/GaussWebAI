package com.mg.gateWay.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.mg.gateWay.model.SystemRuleDTO;
import com.mg.gateWay.model.HoarderResponse;
import com.mg.gateWay.model.RequestData;
import com.mg.gateWay.model.RuleDTO;
import com.mg.gateWay.model.RuleGroupDTO;
import com.mg.gateWay.model.SiteDTO;

/** Singleton scoped Kafka template initiator service class */

@Service
public class ProducerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KafkaTemplate<String, RequestData> kafkaRequestDataTemplate;

	@Autowired
	private UserManagementService userManagementService;

	@Value("${kafka.kafkaTopic}")
	private String kafkaTopic;
	
	@Value("${WordPressTriggers.trigger}")
	private String reqTrigger;
	
	@Value("${WordPressTriggers.site}")
	private String reqSite;

	@Value("${WordPressTriggers.token}")
	private String reqSiteToken;

	public HoarderResponse send(RequestData request) {
		
		//get site details from usermanagement persistent data
		SiteDTO site = userManagementService.getSitebyName(request.getMetaData().get(reqSite), request.getMetaData().get(reqSiteToken));
		
		HoarderResponse response = isSiteRegistered(request, site);
		logger.info("Is site registered [" + request.getMetaData().get(reqSite) + "] result [" + response.getResult() + "]");
		
		if (response.getResult() == true) 
		{
			List<RuleDTO> validRules = getValidActions(request, site);
			
			if (validRules == null || validRules.size() == 0) {
				response.setResult(false);
				response.setMessage("no valid action");
				return response;
			} else {
				request.setRules(validRules);
				kafkaRequestDataTemplate.send(kafkaTopic, request);
				response.setMessage("request accepted");
				response.setResult(true);
				return response;
			}
		} else {

			return response;
		}

	}

	private HoarderResponse isSiteRegistered(RequestData request, SiteDTO site) {
		HoarderResponse response = new HoarderResponse();

		if (site == null) {
			response.setMessage("site [" + request.getMetaData().get(reqSite) + "] not registered.");
			response.setResult(false);
		} else {
			response.setResult(true);
		}
		return response;
	}

	private List<RuleDTO> getValidActions(RequestData request, SiteDTO site) {
		
		List<RuleDTO> responseRules = new ArrayList<RuleDTO>();
	
		logger.info("Retrieve all rule groups for the site", site.getName());
		List<RuleGroupDTO> ruleGroups = site.getRuleGroups();
		
		for (RuleGroupDTO ruleGroup : ruleGroups) 
		{
			logger.info("Retrieve all rules within rule group for site:", site.getName());
			List<RuleDTO> rules = ruleGroup.getRules();
			
			for(RuleDTO rule: rules) {
				SystemRuleDTO systemRule = rule.getSystemRule();
				
				String eventTriggerDesc= systemRule.getSystemEvent().getDescription();
				
				logger.info("Verify if the rule is in enabled state");
				if((rule.getEnabled() == null) || (rule.getEnabled() == true)) {
					
					logger.info("Verify if the rule is associated with request's trigger");
					if(eventTriggerDesc.equals(request.getMetaData().get(reqTrigger))) {
						responseRules.add(rule);
					}
				}
				
			}
		}
		return responseRules;
	}

}
