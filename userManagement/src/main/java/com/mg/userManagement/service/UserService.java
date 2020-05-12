package com.mg.userManagement.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.mg.userManagement.entity.User;
import com.mg.userManagement.repo.UserRepository;

/**Underlying service class for all user entity related operations*/
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public User create(User user)
	{
		logger.info("First-time login; registering user: "+user);
		try{
		return userRepository.save(user);
		}
		catch (Exception ex) {
			logger.error("Registration process failed for user: "+user);
			logger.error("Error stack: ", ex);
			return null;
		}
	}
	
	public List<User> getAll()
	{
		logger.info("Listing all users");
		return userRepository.findAll();
	}
	
	public User getUserById(Integer id) throws Exception
	{
		java.util.Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent())
		{
			logger.info("User already exists for GaussWebAI");
			return userOptional.get();
		}
		else
		{
			logger.info("No existing record found for the user");
			throw new Exception("User ["+id+"] not valild");
		}
		
		
	}
	
	public boolean isUservalid(String name, String password)
	{
		Optional<User> userOptional = userRepository.findByNameAndPassword(name, password);
		if (userOptional.isPresent())
			return true;
		else
			return false;
	}
	
}
