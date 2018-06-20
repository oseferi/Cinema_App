package com.al.akademia.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.al.akademia.utility.HibernateUtil;
import com.al.akademia.entitete.Cinema;

public class CinemaDao {
	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	
	
	public static Cinema getThisCinema() {
		Cinema cinemaTemp = null;
		try {
			
			transObj = sessionObj.beginTransaction();
			cinemaTemp = (Cinema) sessionObj.get(Cinema.class, 32);
	        cinemaTemp =(Cinema) sessionObj.merge(cinemaTemp);
	       
			transObj.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return cinemaTemp;
	}
	


	
	

}
