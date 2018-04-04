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
@Table(name="Flight")
public class Flight {

	private Integer FlightNo;
	private String flightNumber;
	private Integer OriginID;
	private Integer DestinationID;
	private Integer TypeID;
	
	
	private City city;
	private City city1;
	private Ftype ftype;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getFlightNo() {
		return FlightNo;
	}
	public void setFlightNo(Integer flightNo) {
		FlightNo = flightNo;
	}
	@Transient
	public Integer getOriginID() {
		return OriginID;
	}
	public void setOriginID(Integer originID) {
		OriginID = originID;
	}
	@Transient
	public Integer getDestinationID() {
		return DestinationID;
	}
	public void setDestinationID(Integer destinationID) {
		DestinationID = destinationID;
	}
	@Transient
	public Integer getTypeID() {
		return TypeID;
	}
	public void setTypeId(Integer typeId) {
		TypeID = typeId;
	}
	
	
	@ManyToOne
	@JoinColumn(name="OriginID",nullable=false)
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
	@ManyToOne
	@JoinColumn(name="TypeId",nullable=false)
	public Ftype getFtype() {
		return ftype;
	}
	public void setFtype(Ftype ftype) {
		this.ftype = ftype;
	}
	
	@ManyToOne
	@JoinColumn(name="DestinationID")
	public City getCity1() {
		return city1;
	}
	public void setCity1(City city1) {
		this.city1 = city1;
	}
	
	@Column
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	
	
	
}
