package com.mg.gateWay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mg.gateWay.security.JwtService;

/**Authentication controller class*/
@RestController
public class JwtTokenController {

	//@Autowired
	private AuthenticationManager authenticationManager;

	//@Autowired
	private JwtService jwtService;

	//@Autowired
	private UserDetailsService userDetailsService;
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = "/token/{user}/{pass}")
	public ResponseEntity<?> getJwtToken(@PathVariable String user, @PathVariable String pass) throws Exception {
		System.out.println("----------------------------------------------------------------"+user +" : "+pass);
		try {
			//Authenticate the credentials based on user provided username and password
			authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(user, pass));
			logger.debug("Authentication success!");
		}
		catch (BadCredentialsException e) {
			logger.error(e.getMessage());
			throw new Exception("Incorrect username or password", e);
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(user);
		String jwt = jwtService.generateToken(userDetails);
		logger.debug("Security token generated");
		return ResponseEntity.ok(jwt);
	}
}
