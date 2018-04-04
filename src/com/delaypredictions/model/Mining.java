package com.delaypredictions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mining")
public class Mining {
	
	private Integer resultId;
	private Integer setId;
	private Integer categoryAttr;
	private Integer setCount;
	private Integer minSupport;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getResultId() {
		return resultId;
	}
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	
	@Column
	public Integer getSetId() {
		return setId;
	}
	public void setSetId(Integer setId) {
		this.setId = setId;
	}
	
	@Column
	public Integer getCategoryAttr() {
		return categoryAttr;
	}
	public void setCategoryAttr(Integer categoryAttr) {
		this.categoryAttr = categoryAttr;
	}
	
	@Column
	public Integer getSetCount() {
		return setCount;
	}
	public void setSetCount(Integer setCount) {
		this.setCount = setCount;
	}
	@Column
	public Integer getMinSupport() {
		return minSupport;
	}
	public void setMinSupport(Integer minSupport) {
		this.minSupport = minSupport;
	}

	
	
	
}
