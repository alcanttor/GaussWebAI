package com.mg.gateWay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.gateWay.security.MyUserDetailsService;
import com.mg.gateWay.security.User;

@RestController
public class UserController {

	@Autowired
	MyUserDetailsService userService;
	
	@PostMapping("/user")
	public String save(@RequestBody User user)
	{
		userService.save(user);
		return "user saved";
	}
	
	@PutMapping("/user/{userName}")
	public String update(@PathVariable String userName, @RequestBody User user)
	{
		User userOld = userService.get(userName);
		if(userOld == null)
		{
			return "user does not exist";
		}
		else
		{
			userService.save(user);
			return "user updated";
		}
		
	}
	
	
}
