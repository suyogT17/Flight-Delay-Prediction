package com.delaypredictions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="flightstop")
public class FlightStop {

	private Integer flightStopId;
	private Integer flightNo;
	private Integer cityId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getFlightStopId() {
		return flightStopId;
	}
	public void setFlightStopId(Integer flightStopId) {
		this.flightStopId = flightStopId;
	}
	
	@Column
	public Integer getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(Integer flightNo) {
		this.flightNo = flightNo;
	}
	
	@Column
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
	

	
	
	
}
