package com.mg.userManagement.dtoservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.mg.userManagement.dto.UserCreateDTO;
import com.mg.userManagement.entity.User;
import com.mg.userManagement.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceDTO {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	public UserCreateDTO createUser(UserCreateDTO userCreateDTO) {
		User user = modelMapper.map(userCreateDTO, User.class);
		return modelMapper.map(userService.create(user), UserCreateDTO.class);
	}
}