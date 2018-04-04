package com.delaypredictions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.delaypredictions.dao.RefMasterMaintainDAOImpl;
import com.delaypredictions.model.User;
import com.delaypredictions.service.Apriori;
import com.delaypredictions.service.UserService;
import com.delaypredictions.util.ApplicationConstantsUtil;


@Controller
public class DashboardController {

	@Autowired
	Apriori apriori;
	
	@Autowired
	UserService userService;
	

	
	
	@RequestMapping(value="/login.html",method=RequestMethod.GET)
	public String login() {
			
			return "login";
	}
	

	@RequestMapping(value="/error.html",method=RequestMethod.GET)
	public String error() {
			
			return "error";
	}
	
	
	@RequestMapping(value="/registeruser.html",method=RequestMethod.GET)
	public ModelAndView registerUser() {
		
		ModelAndView view = new ModelAndView("register","register",new User());
		return view;
		
	}
	
	
	@RequestMapping(value="/register.html",method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("register") User user) {
		
		ModelAndView modelAndView=new ModelAndView(new RedirectView("login.html"));
		
		userService.registerUser(user);
		return modelAndView;
	}
	
	@RequestMapping(value="/dashboard.html",method=RequestMethod.GET)
	public ModelAndView authenticationHandler() throws Exception
	{
	
		System.out.println("Inside dashboar controller");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
                
        if(role.contains(ApplicationConstantsUtil.USER_ROLE))
        {
        	apriori.processApriori();
        	ModelAndView view = new ModelAndView(new RedirectView("predictioninput.html",
    				true));
    		return view;
        }
        else
        {
        	
        	ModelAndView view = new ModelAndView(new RedirectView("viewalltransactions.html",
    				true));
    		return view;
        }
		
	}

	
	
}
