package com.mg.userManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mg.userManagement.dto.AuthorizationTokenResponse;
import com.mg.userManagement.entity.Site;
import com.mg.userManagement.entity.User;
import com.mg.userManagement.service.JwtService;
import com.mg.userManagement.service.OldUserAuthenticator;
import com.mg.userManagement.service.SiteService;
import com.mg.userManagement.service.UserService;

/**Authentication controller class*/
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class JwtTokenController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private OldUserAuthenticator oldUserAuthenticator;
	
	@Autowired
	private UserService userService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = "/token/{user}/{pass}")
	public AuthorizationTokenResponse getJwtToken(@PathVariable String user, @PathVariable String pass) throws Exception {
		try {
			boolean isValidUser = oldUserAuthenticator.isOldUser(user,pass);
			if (isValidUser)
			{
				//saveUser;
				User newUser = new User();
				newUser.setUsername(user);
				newUser.setPassword(pass);
				userService.create(newUser);
			}
			//Authenticate the credentials based on user provided username and password
			authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(user, pass));
			logger.info("Authentication success!");
		}
		catch (BadCredentialsException e) {
			logger.error(e.getMessage());
			throw new Exception("Incorrect username or password", e);
		}
		User userDetails = (User) userDetailsService.loadUserByUsername(user);
		String jwt = jwtService.generateToken(userDetails);
		logger.debug("Security token generated");
		AuthorizationTokenResponse response = new AuthorizationTokenResponse();
		response.setJwt(jwt);
		response.setUserId(userDetails.getId());
		return response;
	}
	
	@GetMapping(value = "/tokenApp/{siteToken}/{siteName}")
	public AuthorizationTokenResponse getJwtTokenbySiteToken(@PathVariable String siteToken, @PathVariable String siteName) throws Exception {
			Site site = siteService.getSitebyNameandToken(siteName, siteToken);
			System.out.println("site found : "+site);
		if (site == null)
			throw new Exception("Token not valid");
		String jwt = jwtService.generateToken(site.getUser().getUsername());
		logger.debug("Security token generated");
		AuthorizationTokenResponse response = new AuthorizationTokenResponse();
		response.setJwt(jwt);
		response.setUserId(null);
		return response;
	}

}
