package com.mg.gateWay.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mg.gateWay.model.Actions;
import com.mg.gateWay.model.HoaderResponse;
import com.mg.gateWay.model.RequestData;
import com.mg.gateWay.model.tp.Rule;
import com.mg.gateWay.model.tp.Site;

/**Singleton scoped Kafka template initiator service class*/

@Service
public class ProducerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Autowired
	private KafkaTemplate<String, RequestData> kafkaRequestDataTemplate;
	
	@Autowired
	private UserManagementService userManagementService;
	
	@Value("${kafka.kafkaTopic}")
	private String kafkaTopic;

	public HoaderResponse send(RequestData request) {
		Site site = userManagementService.getSitebyName(request.getSiteName());
		HoaderResponse response = isSiteRegistered(request,site);
		logger.info("Is site registered ["+request.getSiteName()+"] result ["+response.getResult()+"]");
		if (response.getResult() == true)
		{
			List<Actions> validAction = getValidActions(request,site);
			if (validAction == null || validAction.size() ==0)
			{
				response.setResult(false);
				response.setMessage("no valid action");
				return response;
			}
			else
			{
				request.setActions(validAction);
				//kafkaRequestDataTemplate.send(kafkaTopic, request);
				response.setMessage("request accepted");
				response.setResult(true);
				return response;
			}
		}
		else
		{
			
			return response;
		}
		
	    
	}

	
	private HoaderResponse isSiteRegistered (RequestData request,Site site)
	{
		HoaderResponse response = new HoaderResponse();	

		if (site == null)
		{
			response.setMessage("site ["+request.getSiteName()+"] nor registered.");
			response.setResult(false);
		}
		else
		{
			response.setResult(true);
		}
		return response;
	}
	
	private  List<Actions> getValidActions(RequestData request,Site site)
	{
		List<Actions> returnActions = new ArrayList<Actions>();
	
		List<Actions> actions = request.getActions();
		List<Rule> rules = site.getRules();
		for(Actions action: actions)
		{
			for(Rule rule : rules)
			{
				logger.info("check this rule[{}] action[{}]",rule.getSysTrigger(),action);
				if (rule.getSysTrigger().equals(action) && rule.getEnabled() == true)
				{
					returnActions.add(action);
				}
			}
		}
		for(Actions vaction: returnActions)
		{
			logger.info("valid actions [{}]",vaction);
		}
		return returnActions;
	}
	
}
