package com.mg.userManagement.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mg.userManagement.entity.SystemEvent;
import com.mg.userManagement.model.Login;
import com.mg.userManagement.model.SiteToken;
import com.mg.userManagement.service.ActionService;
import com.mg.userManagement.service.SystemEventService;
import com.mg.userManagement.service.UserService;

@CrossOrigin(origins="*",allowedHeaders="*")
@Controller
public class WelcomePageController {
	
	@Autowired
	UserService userService;
	@Autowired
	ActionService actionService;
	@Autowired
	SystemEventService systemEventService;
	
	@RequestMapping(path="/")
	public String welcome(org.springframework.ui.Model model)
	{
		model.addAttribute("MSG", "welcome messade");
		model.addAttribute("login",new Login());
		model.addAttribute("error",false);
		return "index";
	}
	
	@PostMapping(value = "/login")
	public String add(Model model, @ModelAttribute("login") Login loginRequest, HttpSession session) {
		try {
			System.out.println(loginRequest);
			boolean isValidUser = userService.isUservalid(loginRequest.getUsername(),loginRequest.getPassword());
			if (isValidUser) {
				session.setAttribute("USERNAME", loginRequest.getUsername());
				model.addAttribute("actions", actionService.getAll());
				List<SystemEvent> events = systemEventService.getAll();
				model.addAttribute("systemEvents", events);
				model.addAttribute("siteToken", new SiteToken());
				return "landing";
			} else {
				model.addAttribute("error", true);
				model.addAttribute("message", "Invalid Credentials");
				model.addAttribute("login", new Login());
				return "index";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("error", true);
			model.addAttribute("message", ex.getMessage());
			return "login";
			// return "redirect:/login";
		}
	}
}
