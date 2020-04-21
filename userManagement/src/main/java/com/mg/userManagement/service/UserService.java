package com.mg.userManagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mg.userManagement.model.SysUser;
import com.mg.userManagement.repo.UserRepository;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserRepository userRepository;
	
	
	public SysUser create(SysUser user)
	{
		logger.info("to save this user : "+user);
		try{
		return userRepository.save(user);
		}
		catch (Exception e) {
		e.printStackTrace();
		return null;
		}
	}
	
	public List<SysUser> getAll()
	{
		return userRepository.findAll();
	}
	
	public SysUser getUserById(Integer id)
	{
		return userRepository.findById(id).get();
	}
}
