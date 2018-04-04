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
@Table(name="categoryattr")
public class CategoryAttr {

	private Integer categoryAttrId;
	private String categoryAttrName;
	private Integer  categoryId;
	private Category category;
	private Set<FlightDelayAttr> flightDelayAttrs=new HashSet<>();

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getCategoryAttrId() {
		return categoryAttrId;
	}
	public void setCategoryAttrId(Integer categoryAttrId) {
		this.categoryAttrId = categoryAttrId;
	}
	
	@Column
	public String getCategoryAttrName() {
		
		return categoryAttrName;
	}
	public void setCategoryAttrName(String categoryAttrName) {
		this.categoryAttrName = categoryAttrName;
	}
	
	@Transient
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	
	@ManyToOne
	@JoinColumn(name="categoryId",nullable=false)
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@OneToMany(mappedBy="categoryAttr")
	
	public Set<FlightDelayAttr> getFlightDelayAttrs() {
		return flightDelayAttrs;
	}
	public void setFlightDelayAttrs(Set<FlightDelayAttr> flightDelayAttrs) {
		this.flightDelayAttrs = flightDelayAttrs;
	}
	

	
	
}
