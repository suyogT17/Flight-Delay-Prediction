package com.delaypredictions.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.websocket.ClientEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="category")
public class Category {

	private Integer categoryId;
	private String categoryName;
	
	
	private Set<CategoryAttr> categoryAttr=new HashSet<>();


	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	@Column
	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	
	@OneToMany(mappedBy="category")
	public Set<CategoryAttr> getCategoryAttr() {
		return categoryAttr;
	}


	public void setCategoryAttr(Set<CategoryAttr> categoryAttr) {
		this.categoryAttr = categoryAttr;
	}
	
	
	
	
	
	
	
	
}
