package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.entity.User;
import com.mg.userManagement.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value="/getAllUser")
	public List<User> getAll()
	{
		return userService.getAll();
	}
	
	@PostMapping(value="/saveUser")
	public User save(@RequestBody User user)
	{
		System.out.println("user in controller : "+user);
		return userService.create(user);
	}
}
