package com.mg.userManagement.dtoservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.mg.userManagement.dto.UserDTO;
import com.mg.userManagement.entity.User;
import com.mg.userManagement.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceDTO {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	public UserDTO createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		User savedUser = userService.create(user);
		return modelMapper.map(savedUser, UserDTO.class);
	}
}