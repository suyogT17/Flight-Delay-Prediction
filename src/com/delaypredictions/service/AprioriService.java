package com.delaypredictions.service;

import java.util.Set;

import com.delaypredictions.dao.AbstractDAO;
import com.delaypredictions.dao.RefMasterMaintainDAOImpl;
import com.delaypredictions.model.Mining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AprioriService {
	
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RefMasterMaintainDAOImpl refMasterMaintainDAOImpl;
	
	
    
	
	public void saveAprioriRecord(Set<Integer> itemset, int iterationNumber, int item_support, int min_support){
		
		System.out.println("one");
		for(Integer i:itemset)
		{
			Mining aprioriResult = new Mining();
			aprioriResult.setSetId(iterationNumber);
			aprioriResult.setCategoryAttr(i);
			aprioriResult.setSetCount(item_support);
			aprioriResult.setMinSupport(min_support);
			System.out.println("For"+i);
			try {
				refMasterMaintainDAOImpl.saveEntity(aprioriResult);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void clearRecords()
	{
		refMasterMaintainDAOImpl.removeAprioriRecords();
	}
}
