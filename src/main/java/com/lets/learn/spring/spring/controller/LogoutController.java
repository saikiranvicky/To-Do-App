package com.lets.learn.spring.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;

//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogoutController {
	
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String LetsLogout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//model.put("name", getLoggedinUserName() );
		if(auth!=null) {
			new SecurityContextLogoutHandler().logout(request,response,auth);
		}
		return "redirect:/";
	}
	
}
