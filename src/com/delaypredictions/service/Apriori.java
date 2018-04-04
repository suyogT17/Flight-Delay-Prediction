package com.delaypredictions.service;
/*
	Java implementation of the Apriori Algorithm
	Author: Manav Sanghavi		Author Link: https://www.facebook.com/manav.sanghavi 
	www.pracspedia.com

	SQL Queries for database:
	
	CREATE TABLE apriori(transaction_id int, object int);
	
	INSERT INTO apriori VALUES(1, 1);
	INSERT INTO apriori VALUES(1, 3);
	INSERT INTO apriori VALUES(1, 4);
	INSERT INTO apriori VALUES(2, 2);
	INSERT INTO apriori VALUES(2, 3);
	INSERT INTO apriori VALUES(2, 5);
	INSERT INTO apriori VALUES(3, 1);
	INSERT INTO apriori VALUES(3, 2);
	INSERT INTO apriori VALUES(3, 3);
	INSERT INTO apriori VALUES(3, 5);
	INSERT INTO apriori VALUES(4, 2);
	INSERT INTO apriori VALUES(4, 5);

*/

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.*;


@Service
public class Apriori {
	 Set<Tuple> c;
	 Set<Tuple> l;
	 int d[][];
	 int min_support;
	 int iterationNumber;
	
	@Autowired
	private  AprioriService aprioriService;
	
	public  void processApriori() throws Exception {
		//aprioriService.clearRecords();
		iterationNumber =1;
		getDatabase();
		c = new HashSet<>();
		l = new HashSet<>();
		Scanner scan = new Scanner(System.in);
		int i, j, m, n;
		//System.out.println("Enter the minimum support (as an integer value):");
		min_support =5 ;
		Set<Integer> candidate_set = new HashSet<>();
		for(i=0 ; i < d.length ; i++) {
			for(j=0 ; j < d[i].length ; j++) {
				System.out.print("Item number " + (j+1) + " = ");
				System.out.println(d[i][j]);
				candidate_set.add(d[i][j]);
			}
		}
		
		Iterator<Integer> iterator = candidate_set.iterator();
		while(iterator.hasNext()) {
			Set<Integer> s = new HashSet<>();
			s.add(iterator.next());
			Tuple t = new Tuple(s, count(s));
			c.add(t);
		}
		
		prune();
		generateFrequentItemsets();
	}
	
	 int count(Set<Integer> s) {
		int i, j, k;
		int support = 0;
		int count;
		boolean containsElement;
		for(i=0 ; i < d.length ; i++) {
			count = 0;
			Iterator<Integer> iterator = s.iterator();
			while(iterator.hasNext()) {
				int element = iterator.next();
				containsElement = false;
				for(k=0 ; k < d[i].length ; k++) {
					if(element == d[i][k]) {
						containsElement = true;
						count++;
						break;
					}
				}
				if(!containsElement) {
					break;
				}
			}
			if(count == s.size()) {
				support++;
			}
		}
		return support;
	}
	
	 void prune() {
		l.clear();
		Iterator<Tuple> iterator = c.iterator();
		while(iterator.hasNext()) {
			Tuple t = iterator.next();
			if(t.support >= min_support) {
				l.add(t);
			}
		}
		
		
		
		for(Tuple t : l) {
			
			if(t.itemset.size()>1)
			{
				aprioriService.saveAprioriRecord(t.itemset,iterationNumber,t.support,min_support);
				iterationNumber++;
			}
		}
	}
	
	 void generateFrequentItemsets() {
		boolean toBeContinued = true;
		int element = 0;
		int size = 1;
		Set<Set> candidate_set = new HashSet<>();
		while(toBeContinued) {
			candidate_set.clear();
			c.clear();
			Iterator<Tuple> iterator = l.iterator();
			while(iterator.hasNext()) {
				Tuple t1 = iterator.next();
				Set<Integer> temp = t1.itemset;
				Iterator<Tuple> it2 = l.iterator();
				while(it2.hasNext()) {
					Tuple t2 = it2.next();
					Iterator<Integer> it3 = t2.itemset.iterator();
					while(it3.hasNext()) {
						try {
							element = it3.next();
						} catch(ConcurrentModificationException e) {
							// Sometimes this Exception gets thrown, so simply break in that case.
							break;
						}
						temp.add(element);
						if(temp.size() != size) {
							Integer[] int_arr = temp.toArray(new Integer[0]);
							Set<Integer> temp2 = new HashSet<>();
							for(Integer x : int_arr) {
								temp2.add(x);
							}
							candidate_set.add(temp2);
							temp.remove(element);
						}
					}
				}
			}
			Iterator<Set> candidate_set_iterator = candidate_set.iterator();
			while(candidate_set_iterator.hasNext()) {
				Set s = candidate_set_iterator.next();
				// These lines cause warnings, as the candidate_set Set stores a raw set.
				c.add(new Tuple(s, count(s)));
			}
			prune();
			if(l.size() <= 1) {
				toBeContinued = false;
			}
			size++;
		}
	}
	
	 void getDatabase() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delayprediction","root","");
		
		Statement s1 = con.createStatement();
		
		s1.executeUpdate("TRUNCATE TABLE mining;");
		
		
		
		Statement s = con.createStatement();
		
		ResultSet rs = s.executeQuery("SELECT * FROM flightdelayattr;");
		Map<Integer, List <Integer>> m = new HashMap<>();
		List<Integer> temp;
		while(rs.next()) {
			int list_no = Integer.parseInt(rs.getString("flightHistoryId"));
			int object = Integer.parseInt(rs.getString("categoryAttrId"));
			temp = m.get(list_no);
			if(temp == null) {
				temp = new LinkedList<>();
			}
			temp.add(object);
			m.put(list_no, temp);
		}
		
		Set<Integer> keyset = m.keySet();
		d = new int[keyset.size()][];
		Iterator<Integer> iterator = keyset.iterator();
		int count = 0;
		while(iterator.hasNext()) {
			temp = m.get(iterator.next());
			Integer[] int_arr = temp.toArray(new Integer[0]);
			d[count] = new int[int_arr.length];
			for(int i=0 ; i < d[count].length ; i++) {
				d[count][i] = int_arr[i].intValue();
			}
			count++;
		}
	}
}

/*

OUTPUT:

Enter the minimum support (as a floating point value, 0<x<1):
0.5
Transaction Number: 1:
Item number 1 = 1
Item number 2 = 3
Item number 3 = 4
Transaction Number: 2:
Item number 1 = 2
Item number 2 = 3
Item number 3 = 5
Transaction Number: 3:
Item number 1 = 1
Item number 2 = 2
Item number 3 = 3
Item number 4 = 5
Transaction Number: 4:
Item number 1 = 2
Item number 2 = 5
-+- L -+-
[1] : 2
[3] : 3
[2] : 3
[5] : 3
-+- L -+-
[2, 3] : 2
[3, 5] : 2
[1, 3] : 2
[2, 5] : 3
-+- L -+-
[2, 3, 5] : 2

=+= FINAL LIST =+=
[2, 3, 5] : 2

*/