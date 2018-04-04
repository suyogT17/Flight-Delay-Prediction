package com.delaypredictions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delaypredictions.dao.RefMasterMaintainDAOImpl;
import com.delaypredictions.dto.FlightAddDTO;
import com.delaypredictions.model.City;
import com.delaypredictions.model.Flight;
import com.delaypredictions.model.FlightFreq;
import com.delaypredictions.model.FlightSchedulde;
import com.delaypredictions.model.FlightStop;
import com.delaypredictions.model.Ftype;
import com.delaypredictions.util.ApplicationConstantsUtil;

@Service
public class FlightService {

	
	@Autowired
	RefMasterMaintainDAOImpl refMasterMaintainDAOImpl;
	
	public void saveFlight(FlightAddDTO flightAddDTO) {
		
		Flight flight=new Flight();
		
		City city=new City();
		city.setCityId(flightAddDTO.getOrigin());
		flight.setCity(city);
		
		City city1=new City();
		city1.setCityId(flightAddDTO.getDestination());
		flight.setCity1(city1);
		
		Ftype ftype=new Ftype();
		ftype.setFtypeid(flightAddDTO.getType());
		flight.setFtype(ftype);
		
		flight.setFlightNumber(flightAddDTO.getFlightNumber());
		
		
		refMasterMaintainDAOImpl.saveEntity(flight);
		
		if(!(flightAddDTO.getFlightStop()== 0)) {
		FlightStop flightStop=new FlightStop();
		
		System.out.println(flightAddDTO.getFlightStop());
		flightStop.setCityId(flightAddDTO.getFlightStop());
		
		flightStop.setFlightNo(flight.getFlightNo());
		refMasterMaintainDAOImpl.saveEntity(flightStop);
		}
		
		for(String classType:flightAddDTO.getClassType()) {
			
			FlightSchedulde flightSchedulde=new FlightSchedulde();
			flightSchedulde.setCategory(flightAddDTO.getCategory());
			flightSchedulde.setClassType(classType);
			flightSchedulde.setFlight(flight);
			flightSchedulde.setStartTime(flightAddDTO.getStartTime());
			flightSchedulde.setEndTime(flightAddDTO.getEndTime());
			
			if(classType.equals(ApplicationConstantsUtil.ECONOMY)) {
			
				flightSchedulde.setPrice(flightAddDTO.getPrice());
			}
			
			else {
				flightSchedulde.setPrice(flightAddDTO.getPrice()+(flightAddDTO.getPrice()*ApplicationConstantsUtil.BUSINESS_CLASS_PRICE));  // for business class 20% extra than the economy class
			}
			
			refMasterMaintainDAOImpl.saveEntity(flightSchedulde);
			
		}
		
		FlightFreq flightFreq=new FlightFreq();
		flightFreq.setFlight(flight);
		flightFreq.setSun(0);
		flightFreq.setMon(0);
		flightFreq.setTue(0);
		flightFreq.setWed(0);
		flightFreq.setThu(0);
		flightFreq.setFri(0);
		flightFreq.setSat(0);
		
		for(String day:flightAddDTO.getDay()) {
			
			switch(day) {
			
			case "sun":
				flightFreq.setSun(1);
				break;
			case "mon":
				flightFreq.setMon(1);
				break;
			case "tue":
				flightFreq.setTue(1);
				break;
			case "wed":
				flightFreq.setWed(1);
				break;
			case "thu":
				flightFreq.setThu(1);
				break;
			case "fri":
				flightFreq.setFri(1);
				break;
			case "sat":
				flightFreq.setSat(1);
				break;	
			
			}
			
		}
		
		refMasterMaintainDAOImpl.saveEntity(flightFreq);
	}
	
	
}
