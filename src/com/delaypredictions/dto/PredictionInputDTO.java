package com.delaypredictions.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;

public class PredictionInputDTO {

	List<Integer> predictionInputList=new ArrayList<>();

	public List<Integer> getPredictionInputList() {
		return predictionInputList;
	}

	public void setPredictionInputList(List<Integer> predictionInputList) {
		this.predictionInputList = predictionInputList;
	}


}

