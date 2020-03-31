package com.mg.gateWay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**Class to initiate the Spring boot container for gateWay application*/

@SpringBootApplication
public class EventProducerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EventProducerApplication.class, args);
		final Logger logger = LoggerFactory.getLogger(EventProducerApplication.class);
		logger.debug("GaussWebAI gateWay application initiated");
	}

}
