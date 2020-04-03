package com.mg.gaussWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***Main controller class for events from Kafka tokenised to be handled by Java*/
@SpringBootApplication
public class EventConsumerApplication {

	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(EventConsumerApplication.class);
		SpringApplication.run(EventConsumerApplication.class, args);
		logger.debug("Java Event Consumer Application Initiated!");
	}

}
