package com.delaypredictions.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Helper {

	public  static String generatePin() throws Exception {
		final Random generator = new Random();
		generator.setSeed(System.currentTimeMillis());
		String genCode = Integer.toString(generator.nextInt(899999) + 100000);
		return genCode ;
	}
	
	public static String getUsername()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("in helper authentication: "+authentication);
		if ((authentication == null)
				|| authentication.getPrincipal().equals("anonymousUser"))
		{
			return null;
		}
		else
		{
			
			return authentication.getName();
		}
	}
	
	/*public static Date formatDate(String dateString) throws ParseException {
		
		Date date=new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
		return date;
		
	}*/
	
	public static Date formatDate(String dateString) throws ParseException {
		
		Date date=new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		return date;
		
	} 
	
	
	public static int difference(Date d1,Date d2) {
		
		long diff = Math.abs(d1.getTime() - d2.getTime());
		int diffDays =(int) diff / (24 * 60 * 60 * 1000);
		 
		return diffDays;
		
	}
	
	public static String getDay(Date date) {
	 SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
     String day=simpleDateformat.format(date);
	return day;
	}
	
	
	
}
