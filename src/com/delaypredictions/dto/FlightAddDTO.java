package com.delaypredictions.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

public class FlightAddDTO {

	private String flightNumber;
	private Integer origin;
	private Integer destination;
	private Integer type;
	private Integer flightStop;
	private String startTime;
	private String endTime;
	private List<String> classType=new ArrayList<String>();
	private Double price;
	private String category;
	private List<String> day=new ArrayList<>();
	
	
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public Integer getOrigin() {
		return origin;
	}
	public void setOrigin(Integer origin) {
		this.origin = origin;
	}
	public Integer getDestination() {
		return destination;
	}
	public void setDestination(Integer destination) {
		this.destination = destination;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getFlightStop() {
		return flightStop;
	}
	public void setFlightStop(Integer flightStop) {
		this.flightStop = flightStop;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public List<String> getClassType() {
		return classType;
	}
	public void setClassType(List<String> classType) {
		this.classType = classType;
	}
	
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<String> getDay() {
		return day;
	}
	public void setDay(List<String> day) {
		this.day = day;
	}
	
	
	
	
	
}
