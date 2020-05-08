package com.mg.userManagement.dtoservice;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.reflect.TypeToken;
import com.mg.userManagement.dto.AllUsersDTO;
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
	
	public List<AllUsersDTO> getAllUsers() {
		
		List<User> users = userService.getAll();
		
		@SuppressWarnings("serial")
		Type listType = new TypeToken<List<AllUsersDTO>>() {}.getType();
		List<AllUsersDTO> allUsersDTO = modelMapper.map(users, listType);
		
		return allUsersDTO;
	}
}