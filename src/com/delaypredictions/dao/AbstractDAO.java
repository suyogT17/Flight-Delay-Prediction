package com.delaypredictions.dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractDAO {
	
	
	private static Session session; 
	
	public SessionFactory getSessionFactory()
	{
		return HibernateManager.getSessionFactory();
	}
	
	public Session getSession()
	{
		
		if(null!=session)
		{
			return session;
			
		}
		else{
			session = HibernateManager.getSessionFactory().openSession();
			return session;
		}
	}


}
