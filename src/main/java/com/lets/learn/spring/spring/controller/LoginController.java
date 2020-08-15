package com.lets.learn.spring.spring.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String LetsLogin(ModelMap model) {
		model.put("name", getLoggedinUserName() );
		return "welcome";
	}
	
	private String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	}
}
