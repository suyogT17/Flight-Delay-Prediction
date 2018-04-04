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
@Table(name="city")
public class City {
	
	private Integer  CityId;
	private String CityName;
	
	
	private Set<Flight> flight=new HashSet<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getCityId() {
		return CityId;
	}
	public void setCityId(Integer cityId) {
		CityId = cityId;
	}
	
	@Column
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	
	@OneToMany(mappedBy="city")
	public Set<Flight> getFlight() {
		return flight;
	}
	public void setFlight(Set<Flight> flight) {
		this.flight = flight;
	}
	

}
