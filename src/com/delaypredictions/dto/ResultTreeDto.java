package com.delaypredictions.dto;

import java.util.ArrayList;
import java.util.List;

import com.delaypredictions.model.CategoryAttr;

public class ResultTreeDto {

	
private String delay;
private String input;
private List<Result> results;
public String getDelay() {
	return delay;
}
public void setDelay(String delay) {
	this.delay = delay;
}
public List<Result> getResults() {
	return results;
}
public void setResults(List<Result> results) {
	this.results = results;
}
public String getInput() {
	return input;
}
public void setInput(String input) {
	this.input = input;
}	
	


	
}
