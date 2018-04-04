package com.delaypredictions.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="flightdelayattr")
public class FlightDelayAttr {

	private Integer flightDelayAttrId;
	private Integer historyId;
	private Integer categoryAttrId;
	
	
	private CategoryAttr categoryAttr;
	private FlightHistory flightHistory;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getFlightDelayAttrId() {
		return flightDelayAttrId;
	}
	public void setFlightDelayAttrId(Integer flightDelayAttrId) {
		this.flightDelayAttrId = flightDelayAttrId;
	}
	
	
	@Transient
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	
	
	@Transient
	public Integer getCategoryAttrId() {
		return categoryAttrId;
	}
	public void setCategoryAttrId(Integer categoryAttrId) {
		this.categoryAttrId = categoryAttrId;
	}
	
	
	@ManyToOne
	@JoinColumn(name="categoryAttrId",nullable=false)
	public CategoryAttr getCategoryAttr() {
		return categoryAttr;
	}
	public void setCategoryAttr(CategoryAttr categoryAttr) {
		this.categoryAttr = categoryAttr;
	}
	
	@ManyToOne
	@JoinColumn(name="flightHistoryId",nullable=false)
	public FlightHistory getFlightHistory() {
		return flightHistory;
	}
	public void setFlightHistory(FlightHistory flightHistory) {
		this.flightHistory = flightHistory;
	}
	
	
	
	
	
}
