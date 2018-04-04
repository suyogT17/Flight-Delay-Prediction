package com.delaypredictions.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delaypredictions.dao.RefMasterMaintainDAOImpl;
import com.delaypredictions.dto.FlightSearchDTO;
import com.delaypredictions.dto.SearchResult;
import com.delaypredictions.model.Flight;
import com.delaypredictions.model.FlightFreq;
import com.delaypredictions.model.FlightSchedulde;
import com.delaypredictions.model.FlightStop;
import com.delaypredictions.util.ApplicationConstantsUtil;
import com.delaypredictions.util.Helper;
import com.delaypredictions.util.SearchParameter;

@Service
public class PriceService {

	@Autowired
	RefMasterMaintainDAOImpl refMasterMaintainDAOImpl;
	
	
	public List<SearchResult> getSearchResult(FlightSearchDTO flightSearchDTO) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println("get result");
		System.out.println(flightSearchDTO.getDate());
		Date userDate=Helper.formatDate(flightSearchDTO.getDate());
		String day=Helper.getDay(userDate);
		
		int difference=Helper.difference(new Date(), userDate);
		
		 
		
		List<SearchResult> searchResults=new ArrayList<>();
		
		List<SearchParameter> searchParameters=new ArrayList<>();
		SearchParameter searchParameter1=new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "city.cityId", flightSearchDTO.getOrigin());
		SearchParameter searchParameter2=new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "city1.cityId", flightSearchDTO.getDestination());
		searchParameters.add(searchParameter1);
		searchParameters.add(searchParameter2);
		
		
		System.out.println("day: "+day);
		List<Flight> flightList=refMasterMaintainDAOImpl.findEntityList(Flight.class, searchParameters, null);
		
		System.out.println("got flightList");
		
		if(flightList != null) {
			System.out.println(flightList.size());
		}
		
		List<SearchParameter> searchParameters2=new ArrayList<>();
		SearchParameter searchParameter11=new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "cityId", flightSearchDTO.getDestination());
		searchParameters2.add(searchParameter11);
		List<FlightStop> flightStops=refMasterMaintainDAOImpl.findEntityList(FlightStop.class, searchParameters2,null);
		if(!flightStops.isEmpty()) {
			
			for(FlightStop fd:flightStops) {
				
				List<SearchParameter> searchParameters3=new ArrayList<>();
				SearchParameter searchParameter12=new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "flightNo", fd.getFlightNo());
				searchParameters3.add(searchParameter12);
				List<Flight> flights=refMasterMaintainDAOImpl.findEntityList(Flight.class, searchParameters3,null);
				
				if(!flights.isEmpty()) {
					System.out.println(flights.get(0).getFlightNumber());
					if(flights.get(0).getCity().getCityId() == flightSearchDTO.getOrigin()){
						
						flightList.add(flights.get(0));
					}
				}
				
			}
		}
		
		if(flightList != null) {
			System.out.println(flightList.size());
		}
		
		List<Flight> removeFlight=new ArrayList<>();
		
		for(Flight flight:flightList) {
			
			System.out.println(flight.getFlightNumber());
			List<SearchParameter> search=new ArrayList<>();
			SearchParameter search1=new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "flight.flightNo", flight.getFlightNo());
			search.add(search1);	
			List<FlightFreq> flightFreqs=refMasterMaintainDAOImpl.findEntityList(FlightFreq.class, search, null);
			if(!flightFreqs.isEmpty()) {
				int checkBit=0;
				FlightFreq flightFreq=flightFreqs.get(0);
				switch(day) {
				
				case "Sun":
					checkBit=flightFreq.getSun();
					break;
				case "Mon":
					checkBit=flightFreq.getMon();
					break;
				case "Tue":
					checkBit=flightFreq.getTue();
					break;
				case "Wed":
					checkBit=flightFreq.getWed();
					break;
				case "Thu":
					checkBit=flightFreq.getThu();
					break;
				case "Fri":
					checkBit=flightFreq.getFri();
					break;
				case "Sat":
					checkBit=flightFreq.getSat();
					System.out.println("bit: "+checkBit);
					break;	
				
				}
			
				if(checkBit==0) {
					removeFlight.add(flight);				}
				
			}
			
			
		}
		System.out.println("remove list");
		if(removeFlight != null) {
			System.out.println(removeFlight.size());
		}
		
		for(Flight remove:removeFlight) {
			
			flightList.remove(remove);
		}
		
		System.out.println("after removing");
		
		if(flightList != null) {
			System.out.println(flightList.size());
		}
		
		
		for(Flight flight:flightList) {
			System.out.println(flight.getFlightNumber());
			
			List<SearchParameter> searches=new ArrayList<>();
			SearchParameter s=new SearchParameter(ApplicationConstantsUtil.MC_EQUAL,"flight.flightNo", flight.getFlightNo());
			SearchParameter s1=new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "classType", flightSearchDTO.getClassType());
			searches.add(s);
			searches.add(s1);
			List<FlightSchedulde> schedule=refMasterMaintainDAOImpl.findEntityList(FlightSchedulde.class, searches, null);
			
			if(!schedule.isEmpty()) {
				Double price=0.0;
				Double newPrice=0.0;
				SearchResult searchResult=new SearchResult();
				searchResult.setOrigin(getCityFromId(flight.getCity().getCityId()).getCityName());
				searchResult.setDestination(getCityFromId(flight.getCity1().getCityId()).getCityName());
				searchResult.setStartTime(schedule.get(0).getStartTime());
				searchResult.setEndTime(schedule.get(0).getEndTime());
				searchResult.setFlightNumber(flight.getFlightNumber());
				if(schedule.get(0).getCategory().equals(ApplicationConstantsUtil.MORNING) || schedule.get(0).getCategory().equals(ApplicationConstantsUtil.EVENING)) {
					price=schedule.get(0).getPrice()+(schedule.get(0).getPrice() *ApplicationConstantsUtil.EXTRA_PRICE);
				}
				else {
					price=schedule.get(0).getPrice();
				}
				
				if(difference <= 5) {
					
					newPrice=price+price*ApplicationConstantsUtil.LESS_THAN_FIVE;
				}
				else if(difference > 5 && difference <= 10 ) {
					newPrice=price+price*ApplicationConstantsUtil.LESS_THAN_TEN;
				}
				else if(difference > 10 && difference <= 15) {
					newPrice=price+price*ApplicationConstantsUtil.LESS_THAN_FIFTEEN;
				}
				else {
					newPrice=price;
				}
				
				searchResult.setPrice(newPrice);
				
				searchResults.add(searchResult);
			}
			
			
		}
		System.out.println("search result empty: "+searchResults.isEmpty());
		if(!searchResults.isEmpty()) {
		return searchResults;
		}
		return null;
	}

	
	public  com.delaypredictions.model.City getCityFromId(int id) {
		
		List<SearchParameter> searchParameters=new ArrayList<>();
		SearchParameter searchParameter=new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "cityId", id);
		searchParameters.add(searchParameter);
		List<com.delaypredictions.model.City> cities=refMasterMaintainDAOImpl.findEntityList(com.delaypredictions.model.City.class, searchParameters, null);
		if(!cities.isEmpty()) {
			
			return cities.get(0);
		}
		return null;
		
		
	}
	
}

