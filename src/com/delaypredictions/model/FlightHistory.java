package com.delaypredictions.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="flighthistory")
public class FlightHistory {

	private Integer flightHistoryId;
	private String flightNum;
	private String delay;
	private Date date;
	
	private Set<FlightDelayAttr> flightDelayAttrs=new HashSet<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	
	public Integer getFlightHistoryId() {
		return flightHistoryId;
	}
	
	
	public void setFlightHistoryId(Integer flightHistoryId) {
		this.flightHistoryId = flightHistoryId;
	}
	
	@Column
	public String getFlightNum() {
		return flightNum;
	}
	
	
	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}
	
	@Column
	public String getDelay() {
		return delay;
	}
	

	public void setDelay(String delay) {
		this.delay = delay;
	}
	
	@Column
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@OneToMany(mappedBy="flightHistory")
	public Set<FlightDelayAttr> getFlightDelayAttrs() {
		return flightDelayAttrs;
	}
	public void setFlightDelayAttrs(Set<FlightDelayAttr> flightDelayAttrs) {
		this.flightDelayAttrs = flightDelayAttrs;
	}
	
	
	
	
	
	
	
	
}

