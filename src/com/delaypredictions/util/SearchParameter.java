/*
 * 
 */

/**
 * Class Description -
 * @author Mehul
 */
package com.delaypredictions.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SearchParameter {

	public SearchParameter(String searchType, String searchColumn,
			Object searchValue) {
		this.searchType = searchType;
		this.searchColumn = searchColumn;
		this.searchValue = searchValue;
		setAliasValue();
	}

	public SearchParameter(String searchType, String searchColumn,
			Object searchValue, Object searchValue2) {
		this.searchType = searchType;
		this.searchColumn = searchColumn;
		this.searchValue = searchValue;
		this.searchValue2 = searchValue2;
		setAliasValue();
	}

	public SearchParameter(String searchType, String searchColumn,
			Object searchValue, String searchAlias) {
		this.searchType = searchType;
		this.searchColumn = searchColumn;
		this.searchValue = searchValue;
		addSearchAliasMap(searchAlias, searchAlias);
	}
	
	public SearchParameter(String searchType, String searchColumn,List<Integer>  searchList) {
		this.searchType = searchType;
		this.searchColumn = searchColumn;
		this.searchValue = searchList;
		setAliasValue();
	}
	
	private String searchType;
	private String searchColumn;
	private Object searchValue;
	private Object searchValue2;
	private Map<String, String> searchAliasMap;
	private List<Integer> searchList;
	
	

	public List<Integer> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<Integer> searchList) {
		this.searchList = searchList;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchColumn(String searchColumn) {
		this.searchColumn = searchColumn;
	}

	public String getSearchColumn() {
		return searchColumn;
	}

	public Object getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(Object searchValue) {
		this.searchValue = searchValue;
	}

	public Map<String, String> getSearchAliasMap() {
		return searchAliasMap;
	}

	public void setSearchAliasMap(Map<String, String> searchAliasMap) {
		this.searchAliasMap = searchAliasMap;
	}

	public Object getSearchValue2() {
		return searchValue2;
	}

	public void setSearchValue2(Object searchValue2) {
		this.searchValue2 = searchValue2;
	}

	public void addSearchAliasMap(String aliasKey, String aliasValue) {
		if (this.searchAliasMap == null) {
			this.searchAliasMap = new HashMap<String, String>();
		}
		this.searchAliasMap.put(aliasKey, aliasValue);
	}

	public final void setAliasValue() {
		if (!this.searchColumn
				.contains(ApplicationConstantsUtil.COMMONPROPBO_ACTIVE)) {
			String[] splitArray = this.searchColumn.split("\\.");
			Map<String, String> aliasMap = new ConcurrentHashMap<String, String>();
			if (ApplicationConstantsUtil.INT_CONSTANT_ONE < splitArray.length) {
				int arrayLength = splitArray.length - 1;
				for (int i = 0; i <= arrayLength; i++) {
					if (ApplicationConstantsUtil.INT_CONSTANT_ZERO.equals(i)) {
						if (!aliasMap.containsKey(splitArray[i])) {
							aliasMap.put(splitArray[i], splitArray[i]);
						}
					} else if (i < arrayLength) {
						if (!aliasMap.containsKey(splitArray[i])) {
							aliasMap.put(splitArray[i], splitArray[i - 1] + "."
									+ splitArray[i]);
						}
					} else if (i == arrayLength) {
						setSearchColumn(splitArray[i - 1] + "." + splitArray[i]);
					}
				}
				setSearchAliasMap(aliasMap);
			}
		}
	}
}