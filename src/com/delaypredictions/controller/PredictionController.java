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
import com.delaypredictions.dto.PredictionInputDTO;
import com.delaypredictions.dto.ResultTreeDto;
import com.delaypredictions.model.Category;
import com.delaypredictions.service.ConstructTreeService;
import com.delaypredictions.util.SearchParameter;

@Controller
public class PredictionController {

	@Autowired
	RefMasterMaintainDAOImpl refMasterMaintainDAOImpl;
	
	@Autowired
	ConstructTreeService constructTreeService; 
	
	
	@RequestMapping(value="/predictioninput.html",method=RequestMethod.GET)
	public ModelAndView givePredictions(Model model) {
		ModelAndView view=new ModelAndView("predictioninput","predictionInput",new PredictionInputDTO());
		
		List<SearchParameter> searchParameters=new ArrayList<>();
	

		List<Category> categoryList=refMasterMaintainDAOImpl.findEntityList(Category.class, searchParameters, null);
		
		model.addAttribute("categoryList", categoryList);
		System.out.println(categoryList.get(0).getCategoryAttr());
		
		return view;
	}
	
	
	
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/viewresult.html",method=RequestMethod.POST)
	public ModelAndView viewResult(@ModelAttribute("predictionInput") PredictionInputDTO predictionInputDTO,Model model) {
		
		ModelAndView modelAndView=new ModelAndView("viewresult");
		
		//System.out.println(predictionInputDTO.getPredictionInputList().size());
		System.out.println("here");
		if(predictionInputDTO.getPredictionInputList() == null) {
			System.out.println("i am here");
			return new ModelAndView(new RedirectView("error.html"));
		}
		
		ResultTreeDto resultTreeDtos= constructTreeService.construct(predictionInputDTO);
		
		
		model.addAttribute("resultTreeDtos",resultTreeDtos);
		return modelAndView;
		
	}
	
	
}
