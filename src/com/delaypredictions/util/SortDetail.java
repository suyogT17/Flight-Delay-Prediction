/*
 * 
 */

/**
 * Class Description -This class is used to  sort the details . 
 * 
 * @author IGATE
 */
package com.delaypredictions.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SortDetail {

	private String sortBy;
	private String sortDirection;
	private Map<String, String> searchAliasMap;

	public SortDetail(String sortBy, String sortDirection) {
		this.sortBy = sortBy;
		this.sortDirection = sortDirection;
		setAliasValue();
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	public Map<String, String> getSearchAliasMap() {
		return searchAliasMap;
	}

	public void setSearchAliasMap(Map<String, String> searchAliasMap) {
		this.searchAliasMap = searchAliasMap;
	}

	public void addSearchAliasMap(String aliasKey, String aliasValue) {
		if (this.searchAliasMap == null) {
			this.searchAliasMap = new HashMap<String, String>();
		}
		this.searchAliasMap.put(aliasKey, aliasValue);
	}

	public final void setAliasValue() {
		String[] splitArray = this.sortBy.split("\\.");
		Map<String, String> aliasMap = new ConcurrentHashMap<String, String>();
		if (splitArray.length > ApplicationConstantsUtil.INT_CONSTANT_ONE) {
			int arrayLength = splitArray.length - 1;
			for (int i = 0; i <= arrayLength; i++) {
				if (aliasMap.containsKey(splitArray[i])) {
					continue;
				} else if (i == arrayLength) {
					setSortBy(splitArray[i - 1] + "." + splitArray[i]);
				} else {
					if (ApplicationConstantsUtil.INT_CONSTANT_ZERO.equals(i)) {
						aliasMap.put(splitArray[i], splitArray[i]);
					} else if (i < arrayLength) {
						aliasMap.put(splitArray[i], splitArray[i - 1] + "."
								+ splitArray[i]);
					}
				}
			}
			setSearchAliasMap(aliasMap);
		}

	}
}