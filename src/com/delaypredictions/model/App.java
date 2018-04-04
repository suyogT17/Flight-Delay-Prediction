package com.delaypredictions.model;

import javax.persistence.criteria.CriteriaBuilder.Case;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.delaypredictions.dao.RefMasterMaintainDAOImpl;

public class App {

	public static void main(String args[])
	{
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		System.out.println("done");	
		
	}
}
