package com.delaypredictions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delaypredictions.dao.RefMasterMaintainDAOImpl;
import com.delaypredictions.model.City;
import com.delaypredictions.model.Ftype;
import com.delaypredictions.util.SearchParameter;

@Service
public class ListService {

	@Autowired
	RefMasterMaintainDAOImpl refMasterMaintainDAOImpl;
	
	public List<City> getAllCities(){
		List<SearchParameter> searchParameters =new ArrayList<>();
		List<City> cityList=refMasterMaintainDAOImpl.findEntityList(City.class, searchParameters, null);
		
		return cityList;
	}
	
	
	public List<Ftype> getAllTypes(){
		List<SearchParameter> searchParameters =new ArrayList<>();
		List<Ftype> ftypeList=refMasterMaintainDAOImpl.findEntityList(Ftype.class, searchParameters, null);
		return ftypeList;
		
	}
	
}
