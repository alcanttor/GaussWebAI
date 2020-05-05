package com.mg.userManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**Class to initiate the Spring-boot container for gateWay application*/

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
//@EnableAutoConfiguration
public class UserManagementApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
		final Logger logger = LoggerFactory.getLogger(UserManagementApplication.class);
		logger.debug("usermanagement gateWay application initiated");
	}

}
