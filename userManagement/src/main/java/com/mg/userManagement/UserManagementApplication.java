package com.mg.userManagement;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.mg.userManagement.service.SiteService;

/**Class to initiate the Spring-boot container for gateWay application*/

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
//@EnableAutoConfiguration
public class UserManagementApplication implements CommandLineRunner{
	
	@Autowired
	private SiteService service;
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
		final Logger logger = LoggerFactory.getLogger(UserManagementApplication.class);
		logger.debug("usermanagement gateWay application initiated");
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		service.getSitebyNameandToken("abc.com", "1591441941290");
		
	}

}
