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
@Table(name="flightfreq")
public class FlightFreq {

	private Integer FreqNo;
	private Integer FlightNo;
	private Integer Mon;
	private Integer Tue;
	private Integer Wed;
	private Integer Thu;
	private Integer Fri;
	private Integer Sat;
	private Integer Sun;
	

	private Flight flight;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getFreqNo() {
		return FreqNo;
	}
	public void setFreqNo(Integer freqNo) {
		FreqNo = freqNo;
	}
	
	@Transient
	public Integer getFlightNo() {
		return FlightNo;
	}
	public void setFlightNo(Integer flightNo) {
		FlightNo = flightNo;
	}
	@Column
	public Integer getMon() {
		return Mon;
	}
	public void setMon(Integer mon) {
		Mon = mon;
	}
	@Column
	public Integer getTue() {
		return Tue;
	}
	public void setTue(Integer tue) {
		Tue = tue;
	}
	@Column
	public Integer getWed() {
		return Wed;
	}
	public void setWed(Integer wed) {
		Wed = wed;
	}
	@Column
	public Integer getThu() {
		return Thu;
	}
	public void setThu(Integer thu) {
		Thu = thu;
	}
	@Column
	public Integer getFri() {
		return Fri;
	}
	public void setFri(Integer fri) {
		Fri = fri;
	}
	@Column
	public Integer getSat() {
		return Sat;
	}
	public void setSat(Integer sat) {
		Sat = sat;
	}
	@Column
	public Integer getSun() {
		return Sun;
	}
	public void setSun(Integer sun) {
		Sun = sun;
	}
	
	@ManyToOne
	@JoinColumn(name="FlightNo")
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	
	
	
	
	
}
