package com.delaypredictions.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
 
import com.delaypredictions.dao.RefMasterMaintainDAOImpl;
import com.delaypredictions.dto.FlightAddDTO;
import com.delaypredictions.dto.PredictionInputDTO;
import com.delaypredictions.dto.ResultTreeDto;
import com.delaypredictions.model.City;
import com.delaypredictions.model.Flight;
import com.delaypredictions.model.Ftype;
import com.delaypredictions.service.ConstructTreeService;
import com.delaypredictions.service.FlightService;
import com.delaypredictions.service.ListService;
import com.delaypredictions.service.UserService;
import com.delaypredictions.util.SearchParameter;
import com.sun.javafx.sg.prism.NGShape.Mode;


@Controller
public class AdminController {

	@Autowired
	RefMasterMaintainDAOImpl refMasterMaintainDAOImpl;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FlightService flightService;
	
	@Autowired 
	ListService listService;
	
	@RequestMapping(value="/addflight.html",method=RequestMethod.GET)
	public ModelAndView flightAdd(Model model) {
		
		ModelAndView view =new ModelAndView("addflight","addflight",new FlightAddDTO());
		
		List<City> cityList=listService.getAllCities();
		List<Ftype> ftypesList=listService.getAllTypes();
		
		model.addAttribute("cityList", cityList);
		model.addAttribute("ftypesList", ftypesList);
		
		return view;
	}
	
/*	@RequestMapping(value="/flightadd.html",method=RequestMethod.GET)
	public ModelAndView addDetails(Model model) {
		ModelAndView view=new ModelAndView("flightadd","flightadd",new AdminDTO());
		
		List<SearchParameter> searchParameters=new ArrayList<>();
	

		List<City> categoryList=refMasterMaintainDAOImpl.findEntityList(City.class, searchParameters, null);
		
		model.addAttribute("categoryList", categoryList);
		System.out.println(categoryList.get(0).getCityName());
		
		return view;
	}
*/	
	@RequestMapping(value="/saveflight.html",method=RequestMethod.POST)
	public ModelAndView saveRegistration(@ModelAttribute("flightadd") FlightAddDTO flightAddDTO)
	{
			
		flightService.saveFlight(flightAddDTO);
		ModelAndView view=new ModelAndView(new RedirectView("addflight.html"));	
		return view;
		
	}
	
	

	
	
	
}
