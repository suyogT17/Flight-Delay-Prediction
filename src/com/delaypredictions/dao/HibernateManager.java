package com.delaypredictions.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")
public class HibernateManager {

	private static SessionFactory sessionFactory;
	
	static
	{
		 try {
		      // creating the SessionFactory from hibernate.cfg.xml            
		      sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		    } catch (Throwable ex) {

		    	System.out.println("Error in creating session factory");
		      throw new ExceptionInInitializerError(ex);
		    }
	}
	private HibernateManager()
	{
		
	}
	 public static SessionFactory getSessionFactory()
	 {
		 if(sessionFactory!=null)
			 return sessionFactory;
		 else
		 {
			 try {
			      // creating the SessionFactory from hibernate.cfg.xml            
			      sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
			      return sessionFactory;
			    } catch (Throwable ex) {

			    	System.out.println("Error in creating session factory");
			      throw new ExceptionInInitializerError(ex);
			    } 
		 }
	 }
	
}
