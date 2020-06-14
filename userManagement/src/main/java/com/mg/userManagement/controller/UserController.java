package com.mg.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.UserDTO;
import com.mg.userManagement.dtoservice.UserServiceDTO;
@RestController
public class UserController {
	
	@Autowired
	UserServiceDTO userServiceDTO;
	
	/*@GetMapping(value="/getAllUser")
	public List<AllUsersDTO> getAll()
	{
		return userServiceDTO.getAllUsers();
	}*/
	
	@PostMapping(value="/saveuser")
	public UserDTO save(@RequestBody UserDTO userCreateDTO)
	{
		return userServiceDTO.createUser(userCreateDTO);
	}
}