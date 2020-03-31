package com.mg.gateWay.security;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

	
	private HashMap<String, User> users = new HashMap<String,User>();
	
	@PostConstruct
	public void init()
	{
		users.put("C1",new User("C1","C1"));
		users.put("C2",new User("C2","C2"));
		users.put("C3",new User("C3","C3"));
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = users.get(userName);
		if (user == null)
			throw new UsernameNotFoundException("user ["+userName+"] not found in records");
		else
			return user;
	}

	public User get(String user)
	{
		return users.get(user);
	}
	
	public User save(User user)
	{
		users.put(user.getUsername(), user);
		return user;
	}

	public HashMap<String, User> getAll() {
		return this.users;
	}
	
}
