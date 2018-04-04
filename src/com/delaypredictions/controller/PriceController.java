package com.delaypredictions.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.set.PredicatedSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.delaypredictions.dto.FlightSearchDTO;
import com.delaypredictions.dto.SearchResult;
import com.delaypredictions.model.City;
import com.delaypredictions.model.Ftype;
import com.delaypredictions.service.ListService;
import com.delaypredictions.service.PriceService;
import com.sun.research.ws.wadl.Request;

@Controller
public class PriceController {

	
	@Autowired
	ListService listService;
	
	@Autowired
	PriceService priceService;
	
	@RequestMapping(value="/pricesearch.html",method=RequestMethod.GET)
	public ModelAndView searchFlight(Model model) {
		
		ModelAndView modelAndView=new ModelAndView("pricesearch","pricesearch",new FlightSearchDTO());
		List<City> cityList=listService.getAllCities();
		
		model.addAttribute("cityList", cityList);
		
		return modelAndView;
	}
	
	 
	@RequestMapping(value="/viewflight.html",method=RequestMethod.POST)
	public ModelAndView priceSearch(@ModelAttribute("pricesearch") FlightSearchDTO flightSearchDTO,Model model) throws Exception  {
		
		System.out.println("my result");
		ModelAndView view =new ModelAndView("result");
		List<SearchResult> resultList=priceService.getSearchResult(flightSearchDTO);
		System.out.println(flightSearchDTO.getDate());
		System.out.println("reached here");
		if(! (resultList == null)) {
		
			System.out.println("not empty");
			model.addAttribute("result", resultList);
			model.addAttribute("msg", new String("no"));
		
		}
		
		else {
			System.out.println("Empty");
			model.addAttribute("msg", new String("yes"));
		}
		
		return view;
		
	}
	
}
