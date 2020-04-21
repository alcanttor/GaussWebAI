package com.mg.gateWay.service;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mg.gateWay.model.Actions;
import com.mg.gateWay.model.Event;
import com.mg.gateWay.model.HoaderResponse;
import com.mg.gateWay.model.RequestData;

/**Singleton scoped Kafka template initiator service class*/

@Service
public class ProducerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	//@Autowired
	private KafkaTemplate<String, Event> kafkaEventDataTemplate;
	
	//@Autowired
	private KafkaTemplate<String, RequestData> kafkaRequestDataTemplate;
	
	//@Autowired
//	private SiteService siteService;

	
	@Value("${kafka.kafkaTopic}")
	private String kafkaTopic;

	public HoaderResponse send(RequestData event) {
		RequestData request = event;//new Gson().fromJson(message, RequestData.class);
		HoaderResponse response = null;//isSiteRegistered(request);
		return response;
	    /*logger.info("Is site registered ["+request.getSiteName()+"] result ["+response.getResult()+"]");
		if (response.getResult() == true)
		{
			List<Actions> validAction = getValidActions(request);
			if (validAction == null || validAction.size() ==0)
			{
				response.setResult(false);
				response.setMessage("no valid action");
				return response;
			}
			else
			{
				request.setActions(validAction);
				//kafkaTemplate.send(kafkaTopic, new Gson().toJson(request));
				//kafkaRequestDataTemplate.send(kafkaTopic, event);
				response.setMessage("request accepted");
				response.setResult(true);
				return response;
			}
		}
		else
		{
			
			return response;
		}
		*/
	    
	}

	/*
	public HoaderResponse send(String message) {
		
		RequestData request = new Gson().fromJson(message, RequestData.class);
		HoaderResponse response = isSiteRegistered(request);
	    logger.info("Is site registered ["+request.getSiteName()+"] result ["+response.getResult()+"]");
		if (response.getResult() == true)
		{
			List<Actions> validAction = getValidActions(request);
			if (validAction == null || validAction.size() ==0)
			{
				response.setResult(false);
				response.setMessage("no valid action");
				return response;
			}
			else
			{
				request.setActions(validAction);
				//kafkaTemplate.send(kafkaTopic, new Gson().toJson(request));
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
	
	
	

	private HoaderResponse isSiteRegistered (RequestData request)
	{
		HoaderResponse response = new HoaderResponse();	

		Site site = siteService.getSite(Integer.parseInt(request.getSiteName()));
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
	
	private  List<Actions> getValidActions(RequestData request)
	{
		List<Actions> returnActions = new ArrayList<Actions>();
	
		Site site = siteService.getSite(Integer.parseInt(request.getSiteName()));
		List<Actions> actions = request.getActions();
		List<Rule> rules = site.getRules();
		for(Actions action: actions)
		{
			for(Rule rule : rules)
			{
				if (rule.getSysTrigger().equals(action) && rule.getEnabled() == true)
				{
					returnActions.add(action);
				}
			}
		}
		return returnActions;
	}*/
	
}
