package com.mg.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.AllUsersDTO;
import com.mg.userManagement.dto.UserDTO;
import com.mg.userManagement.dtoservice.UserServiceDTO;
@RestController
public class UserController {
	
	@Autowired
	UserServiceDTO userServiceDTO;
	
	@GetMapping(value="/getAllUser")
	public List<AllUsersDTO> getAll()
	{
		return userServiceDTO.getAllUsers();
	}
	
	@PostMapping(value="/saveUser")
	public UserDTO save(@RequestBody UserDTO userCreateDTO)
	{
		return userServiceDTO.createUser(userCreateDTO);
	}
}