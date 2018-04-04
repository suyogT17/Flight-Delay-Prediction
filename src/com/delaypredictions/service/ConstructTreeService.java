package com.delaypredictions.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sound.midi.Synthesizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delaypredictions.dao.RefMasterMaintainDAOImpl;
import com.delaypredictions.dto.PredictionInputDTO;
import com.delaypredictions.dto.Result;
import com.delaypredictions.dto.ResultTreeDto;
import com.delaypredictions.model.CategoryAttr;
import com.delaypredictions.model.Mining;
import com.delaypredictions.util.ApplicationConstantsUtil;
import com.delaypredictions.util.SearchParameter;

@Service
public class ConstructTreeService {

	@Autowired
	RefMasterMaintainDAOImpl refMasterMaintainDAOImpl;

	public ResultTreeDto construct(PredictionInputDTO predictionInputDTO) {

		ResultTreeDto resultTreeDto=new ResultTreeDto();
		List<Result> results=new ArrayList<>();
		Set<Integer> sets = new HashSet<>();
		Set<Integer> finalSets = new HashSet<>();

		System.out.println(predictionInputDTO.getPredictionInputList());

		    
		for (Integer i : predictionInputDTO.getPredictionInputList()) {
			
			System.out.println("finding with: "+i);
			List<SearchParameter> searchParameters = new ArrayList<>();
			SearchParameter searchParameter1 = new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "categoryAttr",i);
   
			searchParameters.add(searchParameter1);
			List<Mining> miningSets = refMasterMaintainDAOImpl.findEntityList(Mining.class, searchParameters, null);
			System.out.println("size: "+miningSets.size()+"\n");
			
			if (!miningSets.isEmpty()) {
				for (Mining mining : miningSets) {
					System.out.println(mining.getSetId());
					sets.add(mining.getSetId());
				}
			}
			System.out.println("----------------------------");
		}

		System.out.println(sets);
		
		for (Integer setId : sets) {
			Integer inputcount = 0;
			List<SearchParameter> searchParameters = new ArrayList<>();

			SearchParameter searchParameter1 = new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "setId", setId);

			searchParameters.add(searchParameter1);

			List<Mining> miningCategory = refMasterMaintainDAOImpl.findEntityList(Mining.class, searchParameters, null);

			if (!miningCategory.isEmpty()) {
				float sz= (float) miningCategory.size() / 2;
				int size = (int) Math.ceil(sz);
				System.out.println("setId: "+setId+" size: "+miningCategory.size()+"divsize: "+sz);
				for (Mining mining : miningCategory) {

					for (Integer input : predictionInputDTO.getPredictionInputList()) {

						if (mining.getCategoryAttr() == input) {

							inputcount++;

						}
					}
				}
				
				System.out.println("mining cat: "+miningCategory.size()+" inputsize : "+inputcount);
				if (inputcount == miningCategory.size()) {
					System.out.println("yes");
					resultTreeDto.setDelay("yes");
					
				}
				else if (inputcount >= size && inputcount < miningCategory.size() ) {
					resultTreeDto.setDelay("no");
					finalSets.add(setId);
				}
			}

		}

		
		//System.out.println(sets);
		System.out.println(finalSets);
		
		for(Integer i:finalSets) {
			
			List<SearchParameter> searchParameters4=new ArrayList<>();
			SearchParameter searchParameter4=new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "setId", i);
			searchParameters4.add(searchParameter4);
			List<Mining> minings=refMasterMaintainDAOImpl.findEntityList(Mining.class, searchParameters4, null);
			
			if(!(minings == null)) {
				String setsString="";
				for(Mining m:minings) {
					
					List<SearchParameter> search=new ArrayList<>();
					SearchParameter s=new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "categoryAttrId", m.getCategoryAttr());
					search.add(s);
					List<CategoryAttr> categoryList=refMasterMaintainDAOImpl.findEntityList(CategoryAttr.class, search, null);
					setsString=setsString+categoryList.get(0).getCategoryAttrName()+",";
				}
				setsString=setsString+"";
				int last=setsString.lastIndexOf(",");
				setsString=setsString.substring(0,last);
				Result res=new Result();
				res.setPercent(minings.get(0).getSetCount()*10);
				res.setSet(setsString);
				results.add(res);
			}
		}
		
		
		String mainString="";
		for(Integer id:predictionInputDTO.getPredictionInputList()) {
			
			List<SearchParameter> search=new ArrayList<>();
			SearchParameter s=new SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "categoryAttrId", id);
			search.add(s);
			List<CategoryAttr> categoryList=refMasterMaintainDAOImpl.findEntityList(CategoryAttr.class, search, null);
			mainString=mainString+categoryList.get(0).getCategoryAttrName()+",";
		}
		mainString=mainString+"";
		mainString=mainString.substring(0,mainString.lastIndexOf(","));
		
	
		resultTreeDto.setInput(mainString);
		
		
		
		System.out.println("got ll results "+results.size());
		System.out.println(resultTreeDto.getInput()+"  "+resultTreeDto.getDelay());
		for(Result r:results) {
			System.out.println(r.getSet()+"   "+r.getPercent());
		}
		resultTreeDto.setResults(results);
		
		return resultTreeDto;

	}

}

/*
 * System.out.println("nn construct");
 * 
 * List<ResultTreeDto> resultTreeDtos=new ArrayList<>();
 * 
 * for(Integer i:predictionInputDTO.getPredictionInputList()) {
 * 
 * List<CategoryAttr> finalCategoryAttr=new ArrayList<>(); ResultTreeDto
 * resultTreeDto=new ResultTreeDto(); List<SearchParameter> searchParameters=new
 * ArrayList<>();
 * 
 * System.out.println("1");
 * 
 * SearchParameter searchParameter1=new
 * SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "categoryAttrId", i);
 * 
 * System.out.println("2"); searchParameters.add(searchParameter1);
 * List<CategoryAttr>
 * categoryAttrs=refMasterMaintainDAOImpl.findEntityList(CategoryAttr.class,
 * searchParameters , null); if(categoryAttrs.size() != 0) {
 * 
 * resultTreeDto.setMainNode(categoryAttrs.get(0).getCategoryAttrName());
 * 
 * 
 * List<SearchParameter> searchParameters2=new ArrayList<>();
 * 
 * System.out.println("3"); SearchParameter searchParameter2=new
 * SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "categoryAttr",i);
 * 
 * searchParameters2.add(searchParameter2); List<Mining>
 * miningResults=refMasterMaintainDAOImpl.findEntityList(Mining.class,
 * searchParameters2 , null);
 * 
 * if(miningResults.size() != 0 ) {
 * 
 * for(Mining miningResult:miningResults) { List<SearchParameter>
 * searchParameters3=new ArrayList<>(); SearchParameter searchParameter3=new
 * SearchParameter(ApplicationConstantsUtil.MC_EQUAL, "setId",
 * miningResult.getSetId());
 * 
 * System.out.println("Here 1");
 * 
 * SearchParameter searchParameter4=new
 * SearchParameter(ApplicationConstantsUtil.MC_NOT_EQUAL, "categoryAttr", i);
 * 
 * System.out.println("Here 2");
 * 
 * 
 * searchParameters3.add(searchParameter3);
 * searchParameters3.add(searchParameter4); List<Mining>
 * miningList=refMasterMaintainDAOImpl.findEntityList(Mining.class,
 * searchParameters3, null);
 * 
 * if(miningList.size() != 0) { for(Mining mine: miningList) {
 * List<SearchParameter> searchParameters5=new ArrayList<>(); SearchParameter
 * searchParameter5=new
 * SearchParameter(ApplicationConstantsUtil.MC_EQUAL,"categoryAttrId",
 * mine.getCategoryAttr()); searchParameters5.add(searchParameter5);
 * List<CategoryAttr>
 * categoryAttrsList=refMasterMaintainDAOImpl.findEntityList(CategoryAttr.class,
 * searchParameters5, null); if(categoryAttrsList.size() != 0) {
 * finalCategoryAttr.add(categoryAttrsList.get(0)); } }
 * 
 * } }
 * 
 * Set<CategoryAttr> categorySet=new HashSet<>();
 * categorySet.addAll(finalCategoryAttr); finalCategoryAttr.clear();
 * 
 * finalCategoryAttr.addAll(categorySet);
 * resultTreeDto.setCategoryAttr(finalCategoryAttr);
 * 
 * }
 * 
 * 
 * }
 * 
 * 
 * resultTreeDtos.add(resultTreeDto);
 * 
 * }
 * 
 * for(ResultTreeDto dto: resultTreeDtos) {
 * 
 * System.out.println("mainnode: "+dto.getMainNode());
 * 
 * 
 * for(CategoryAttr category:dto.getCategoryAttr()) {
 * System.out.print(category.getCategoryAttrName()+" ,  "); }
 * 
 * System.out.println(); }
 * 
 * 
 * return resultTreeDtos;
 */
