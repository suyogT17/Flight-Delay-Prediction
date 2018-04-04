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
@Table(name="ftype")
public class Ftype {
	
	private Integer ftypeid;
	private String flightype;
	
	private Set<Flight> fligh=new HashSet<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getFtypeid() {
		return ftypeid;
	}
	public void setFtypeid(Integer ftypeid) {
		this.ftypeid = ftypeid;
	}
	
	
	
	
	
	@Column
	public String getFlightype() {
		return flightype;
	}
	public void setFlightype(String flightype) {
		this.flightype = flightype;
	}
	
	
	@OneToMany(mappedBy="ftype")
	public Set<Flight> getFligh() {
		return fligh;
	}
	public void setFligh(Set<Flight> fligh) {
		this.fligh = fligh;
	}
	
	

}
