package com.delaypredictions.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ManyToAny;
@Entity
@Table(name="FlightSchedulde")
public class FlightSchedulde {
	
	private Integer ScheduleId;
	private Integer FlightId;
	private String StartTime;
	private String EndTime;
	private String ClassType;
	private Double Price;
	private String Category;
	
	
	private Flight flight;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getScheduleId() {
		return ScheduleId;
	}
	public void setScheduleId(Integer scheduleId) {
		ScheduleId = scheduleId;
	}
	@Transient
	public Integer getFlightId() {
		return FlightId;
	}
	public void setFlightId(Integer flightId) {
		FlightId = flightId;
	}
	@Column
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	@Column
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	@Column
	public String getClassType() {
		return ClassType;
	}
	public void setClassType(String class1) {
		ClassType = class1;
	}
	@Column
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}
	
	@Column
	public String getCategory() {
		return Category;
	}
	
	public void setCategory(String category) {
		Category = category;
	}
	
	
	@ManyToOne
	@JoinColumn(name="FlightId")
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	
	

}
