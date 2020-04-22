package com.mg.gateWay.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.gateWay.security.MyUserDetailsService;
import com.mg.gateWay.security.SecurityUser;

@RestController
public class UserController {

	//@Autowired
	MyUserDetailsService userService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/user")
	public String save(@RequestBody SecurityUser user)
	{
		userService.save(user);
		
		logger.debug("New user registered");
		return "user saved";
	}
	
	@PutMapping("/user/{userName}")
	public String update(@PathVariable String userName, @RequestBody SecurityUser user)
	{
		logger.debug("Checking for existing username");
		SecurityUser userOld = userService.get(userName);
		if(userOld == null)
		{
			logger.debug("No such user exists in system");
			return "user does not exist";
		}
		else
		{
			logger.debug("Existing user found in the system");
			userService.save(user);
			logger.debug("Existing user details updated");
			return "user updated";
		}
		
	}
	
	@GetMapping("/user")
	public HashMap<String,SecurityUser> getAll()
	{
		return userService.getAll();
	}
	
}
