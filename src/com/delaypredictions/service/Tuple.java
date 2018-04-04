package com.delaypredictions.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class Tuple {

	Set<Integer> itemset;
	int support;
	
	Tuple() {
		itemset = new HashSet<>();
		support = -1;
	}
	
	Tuple(Set<Integer> s) {
		itemset = s;
		support = -1;
	}
	
	Tuple(Set<Integer> s, int i) {
		itemset = s;
		support = i;
	}


}
